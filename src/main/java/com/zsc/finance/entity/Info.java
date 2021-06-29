package com.zsc.finance.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Info {
    private Integer id;

    private Integer sendid;

    private Admin admin;

    private Integer receiveid;

    private User user;

    private Date createtime;

    private String title;

    private String infodesc;

    private Integer status;
}