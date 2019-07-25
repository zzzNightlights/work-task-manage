package com.example.demo.entity;

import lombok.Data;


@Data
public class Mail {
    private Integer mailId;
    private String address;
    private String subject;
    private String Content;
}
