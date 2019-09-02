package com.wms.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.wms.common.enums.PermissionTreeStateEnum;
import com.wms.entity.auto.SysPermissionTEntity;

public class PermissionTreeVO {

	private Long id;
	private String text;
	private Boolean checked = false;
	private String state = PermissionTreeStateEnum.Open.getCode();
	private List<PermissionTreeVO> children;
	private PermissionVO attributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<PermissionTreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionTreeVO> children) {
		this.children = children;
	}

	public PermissionVO getAttributes() {
		return attributes;
	}

	public void setAttributes(PermissionVO attributes) {
		this.attributes = attributes;
	}

	public PermissionTreeVO() {}
	
	public PermissionTreeVO(SysPermissionTEntity per) {
		this.id = per.getPermissionId();
		this.text = per.getPermissionName();
		//this.attributes = new PermissionVo(per);
	}
	
	public PermissionTreeVO(SysPermissionTEntity per, Boolean checked) {
		this.id = per.getPermissionId();
		this.text = per.getPermissionName();
		this.checked = checked;
		//this.attributes = new PermissionVo(per);
	}
	
	public void createTree(Map<Long, List<SysPermissionTEntity>> map, Set<Long> userPerIds) {
		List<SysPermissionTEntity> list = map.get(this.id);
		if (CollectionUtils.isEmpty(list))
			return;
		
		List<PermissionTreeVO> treeList = new ArrayList<PermissionTreeVO>();
		for (SysPermissionTEntity per : list) {
			boolean checked = Boolean.FALSE;
			//有权限并且在子节点
			if (userPerIds.contains(per.getPermissionId()) && !map.containsKey(per.getPermissionId())) {
				checked = Boolean.TRUE;
			}
			PermissionTreeVO tree = new PermissionTreeVO(per, checked);
			tree.createTree(map, userPerIds);
			treeList.add(tree);
		}
		this.setChildren(treeList);
	}

}
