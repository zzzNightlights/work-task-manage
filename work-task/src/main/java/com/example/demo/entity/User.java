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
