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

    public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getExEndDate() {
		return exEndDate;
	}

	public void setExEndDate(String exEndDate) {
		this.exEndDate = exEndDate;
	}

	public String getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(String actEndDate) {
		this.actEndDate = actEndDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

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
