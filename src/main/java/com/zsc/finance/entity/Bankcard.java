package com.zsc.finance.entity;

import lombok.Data;

@Data
public class Bankcard {
    private Integer id;

    private String cardbank;

    private Integer type;

    private String cardnum;

    private Integer userid;

    private User user;
}