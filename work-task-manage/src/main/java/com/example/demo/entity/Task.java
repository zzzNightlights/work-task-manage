package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Task implements Serializable {
    private int taskId;
    private String createDate;
    private int userId;
    private String userName;
    private String taskContent;
    private String exEndDate;
    private String actEndDate;
    private String taskStatus;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", createDate='" + createDate + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", exEndDate='" + exEndDate + '\'' +
                ", actEndDate='" + actEndDate + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
    }
}
