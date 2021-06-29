package com.zsc.finance.entity;

import lombok.Data;

@Data
public class Bank {
    private Integer id;

    private String name;

    private String type;

    private String assets;

    private String bankdesc;
}