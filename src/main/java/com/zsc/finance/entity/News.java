package com.zsc.finance.entity;

import lombok.Data;

import java.util.Date;
@Data
public class News {
    private Integer id;

    private String title;

    private String newsdesc;

    private Date createtime;
}