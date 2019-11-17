package com.wms.vo.mail;

import java.util.List;

public class RequestVO {
	private List<String> to;
	private String subject;
	private String content;
	private String emailType;
	private String operateUser;
	private List<RequestAttachmentVO> attachments;
	
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public List<RequestAttachmentVO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<RequestAttachmentVO> attachments) {
		this.attachments = attachments;
	}
	
}
