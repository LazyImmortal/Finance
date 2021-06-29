package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TermFinancial {
    private Integer id;

    private String name;

    private String investerm;

    private BigDecimal leastmoney;

    private Integer profit;

    private BigDecimal annualincome;
}