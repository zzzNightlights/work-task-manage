package com.example.demo.entity;

import lombok.Data;


@Data
public class Mail {
    private Integer mailId;
    private String address;
    private String subject;
    private String Content;
	public Integer getMailId() {
		return mailId;
	}
	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
    
}
