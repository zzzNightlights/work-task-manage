package com.example.demo.entity;

import lombok.Data;

@Data
public class User {
    private String mail;
    private int userId;
    private String username;
    private String password;
    private int type;
    private String name;
    private String organization;
    private String position;
    private int jobNumber;

    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Override
    public String toString() {
        return "User{" +
                "Mail='" + mail + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", position='" + position + '\'' +
                ", jobNumber=" + jobNumber +
                '}';
    }
}
