package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PayMoney {
    private Integer id;

    private BigDecimal monthmoney;

    private Integer autointo;

    private Integer type;

    private String investerm;
}