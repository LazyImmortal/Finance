package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeMoney {
    private Integer id;

    private String name;

    private BigDecimal annualincome;

    private BigDecimal peiincome;

    private String investerm;

    private BigDecimal invesmoney;
}