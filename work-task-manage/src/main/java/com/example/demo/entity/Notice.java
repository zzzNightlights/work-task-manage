package com.example.demo.entity;

import lombok.Data;

@Data
public class Notice {
    private int noticeId;
    private String noticeText;
    private int userId;
    private String userName;
    private String noticeDate;


    public int getNoticeId() {
		return noticeId;
	}


	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}


	public String getNoticeText() {
		return noticeText;
	}


	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getNoticeDate() {
		return noticeDate;
	}


	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}


	@Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", noticeText='" + noticeText + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", noticeDate='" + noticeDate + '\'' +
                '}';
    }
}
