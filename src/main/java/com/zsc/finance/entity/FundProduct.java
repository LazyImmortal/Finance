package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundProduct {
    private Integer id;

    private Integer type;

    private Integer code;

    private String funddesc;

    private BigDecimal dailygrowth;

    private BigDecimal monthlygrowth;

    private BigDecimal annualgrowth;

    private BigDecimal leastmoney;

    private String investerm;

}