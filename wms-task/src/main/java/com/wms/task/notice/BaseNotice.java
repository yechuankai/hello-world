package com.wms.task.notice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.base.IOwnerService;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.services.sys.IWarehouseActiveService;
import com.wms.task.config.TaskThreadPoolConfig;
import com.wms.vo.mail.RequestAttachmentVO;
import com.wms.vo.mail.RequestVO;
import com.wms.vo.mail.ResponseVO;

/**
 * 通知 邮件方式
 * 
 * @author yechuankai.chnet
 *
 */
public class BaseNotice {

	private static final Logger log = LoggerFactory.getLogger(BaseNotice.class);

	// 获取报表根目录
	protected String getReportRootUrl() {
		Environment env = SpringUtils.getBean(Environment.class);
		String value = env.getProperty("wms.url.report");
		return value;
	}

	// 获取邮件接口地址
	protected String getMailInterfaceUrl() {
		Environment env = SpringUtils.getBean(Environment.class);
		String value = env.getProperty("swp.url.mail");
		return value;
	}

	// 获取启用的公司
	protected List<Long> getCompanys() {
		//获取所有仓库
		IWarehouseActiveService warehouseService = SpringUtils.getBean(IWarehouseActiveService.class);
		List<WarehouseActiveTEntity> wareList = warehouseService.findAll();
		if (CollectionUtils.isEmpty(wareList))
			return Lists.newArrayList();

		return wareList.stream().map(WarehouseActiveTEntity::getCompanyId).collect(Collectors.toList());
	}

	/**
	 * 获取状态数据，一个单多个状态时按最新状态进行发送
	 * 
	 * @param status
	 * @return
	 */
	protected List<StatusHistoryTEntity> getData(StatusHistoryTEntity statusHis, Set<String> status) {
		IStatusHistoryService statusHistory = SpringUtils.getBean(IStatusHistoryService.class);
		List<StatusHistoryTEntity> list = statusHistory.findByNotice1(statusHis, status);

		if (CollectionUtils.isEmpty(list))
			return Lists.newArrayList();

		//按单号分组
		Map<Long, List<StatusHistoryTEntity>> groupMap = list.stream()
				.collect(Collectors.groupingBy(StatusHistoryTEntity::getSourceNumber));

		List<StatusHistoryTEntity> listByOrder = Lists.newArrayList();

		//分组后按时间排序获取最新数据
		groupMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;

			v.sort(new Comparator<StatusHistoryTEntity>() {
				@Override
				public int compare(StatusHistoryTEntity o1, StatusHistoryTEntity o2) {
					return o2.getCreateTime().compareTo(o1.getCreateTime());
				}
			});
			listByOrder.add(v.get(0));
			if (v.size() > 1) {
				//将除第一条外的记录更新为无需处理
				for (int i = 1; i < v.size(); i++) {
					StatusHistoryTEntity selectStatus = v.get(i);
					updateNoticFlag(StatusHistoryTEntity.builder()
										.historyId(selectStatus.getHistoryId())
										.notice1("Skip")
										.updateTime(DateUtils.getNowDate())
										.updateBy("autoMailSend")
										.build());
				}
			}
		});

		return listByOrder;
	}

	//更新处理中
	protected void updateProcessing(StatusHistoryTEntity status) {
		IStatusHistoryService statusService = SpringUtils.getBean(IStatusHistoryService.class);
		status.setNotice1("Processing");
		statusService.modify(status);
	}
	
	//更新完成标识
	protected void updateNoticFlag(StatusHistoryTEntity status) {
		IStatusHistoryService statusService = SpringUtils.getBean(IStatusHistoryService.class);
		if (StringUtils.isNotEmpty(status.getNotice1()) && status.getNotice1().length() > 100) {
			status.setNotice1(status.getNotice1().substring(0, 100));
		}
		statusService.modify(status);
	}

	protected List<String> getRecipient(OwnerTEntity owner) {
		IOwnerService ownerService = SpringUtils.getBean(IOwnerService.class);
		OwnerTEntity selectOwner = ownerService.find(owner);
		List<String> recipients = Lists.newArrayList();
		if (StringUtils.isNotEmpty(selectOwner.getEmail1())) {
			recipients.add(selectOwner.getEmail1());
		}
		if (StringUtils.isNotEmpty(selectOwner.getEmail2())) {
			recipients.add(selectOwner.getEmail2());
		}
		return recipients;
	}

	protected RequestVO generatorReport(String reportUrl, RequestVO request) {
		StringBuilder sb = new StringBuilder();
		String responseCookie = null;
		try {
			// 创建一个URL实例
			URL url = new URL(reportUrl);
			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// 通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
				responseCookie = conn.getHeaderField("Set-Cookie");// 取到所用的Cookie
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				// 为字符输入流添加缓冲
				BufferedReader br = new BufferedReader(isr);
				String data = null;
				while ((data = br.readLine()) != null) {// 循环读取数据
					sb.append(data);
				}
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
		}
		request.setContent(sb.toString());
		if (StringUtils.isNotEmpty(request.getContent())) {
			//轉換附件
			List<RequestAttachmentVO> attachments = getHtmlPicture(responseCookie, request);
			request.setAttachments(attachments);
		}
		return request;
	}

	public List<RequestAttachmentVO> getHtmlPicture(String cookie, RequestVO request) {
		String content = request.getContent();
		if (StringUtils.isEmpty(content))
			return Lists.newArrayList();
		
		//仅匹配HTTP打头的图片
		final String searchImgReg = "(?x)(src|SRC|background|BACKGROUND)=('|\")(http://(.*?))('|\")";
		List<RequestAttachmentVO> imageaAttachments = Lists.newArrayList();
		Pattern pattern = Pattern.compile(searchImgReg);
		Matcher matcher = pattern.matcher(content);
		Set<String> fileNameSet = Sets.newHashSet();
		while (matcher.find()) {
			String imageUrl = matcher.group(3);
			if (fileNameSet.contains(imageUrl)) {
				continue;
			}
			fileNameSet.add(imageUrl);
			try {
				String fileName = new ObjectId().toHexString() + ".jpg";
				URL url = new URL(imageUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("Cookie", cookie);// 给服务器送登录后的cookie
				InputStream is = conn.getInputStream();
				ObjectId id = MongoUtils.store(is, fileName, "");
				is.close();
				
				//替换为cid:文件名
				content = content.replace(imageUrl, "cid:"+fileName);
				
				RequestAttachmentVO vo = new RequestAttachmentVO();
				vo.setObjectId(id.toHexString());
				vo.setFileName(fileName);
				imageaAttachments.add(vo);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		request.setContent(content);
		request.setAttachments(imageaAttachments);
		return imageaAttachments;
	}

	protected void send(RequestVO request, StatusHistoryTEntity status) {
		//异步发送，控制线程执行顺序
		TaskThreadPoolConfig.newThreadPoolTaskExecutor().execute(new Runnable() {
			@Override
			public void run() {
				RestTemplate client = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				HttpMethod method = HttpMethod.POST;
				// 以表单的方式提交
				headers.setContentType(MediaType.APPLICATION_JSON);
				// 将请求头部和参数合成一个请求
				HttpEntity<RequestVO> requestEntity = new HttpEntity<>(request, headers);
				ResponseVO ret = null;
				try {
					log.debug("start send mail...");
					// 执行HTTP请求，将返回的结构使用ResultVO类格式化
					ResponseEntity<ResponseVO> response = client.exchange(getMailInterfaceUrl(), method, requestEntity, ResponseVO.class);
					log.debug("end send mail...");
					ret = response.getBody();
					if (ret == null) {
						ret = new ResponseVO();
						ret.setOperMsg("send mail fail.");
					}
				} catch (Exception e) {
					ret = new ResponseVO();
					log.error(e.getMessage(), e);
					ret.setOperMsg(e.getMessage());
				} finally {
					//删除上传的附件
					if (CollectionUtils.isNotEmpty(request.getAttachments())) {
						request.getAttachments().forEach(v->{
							MongoUtils.delete(new ObjectId(v.getObjectId()));
						});
					}
					// 判断发送结果
					StatusHistoryTEntity updateStatus = StatusHistoryTEntity.builder()
															.historyId(status.getHistoryId())
															.updateTime(DateUtils.getNowDate())
															.updateBy("autoMailSend")
															.build();
					if (YesNoEnum.Yes.getCode().equals(ret.getOperResult())) {
						updateStatus.setNotice1(YesNoEnum.Yes.getCode());
					} else {
						updateStatus.setNotice1(ret.getOperMsg());
					}
					updateNoticFlag(updateStatus);
				}
			}
		});
	}
	
}
