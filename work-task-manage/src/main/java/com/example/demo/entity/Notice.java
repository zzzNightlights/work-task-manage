package com.example.demo.entity;

import lombok.Data;

@Data
public class Notice {
    private int noticeId;
    private String noticeText;
    private int userId;
    private String userName;
    private String noticeDate;


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
