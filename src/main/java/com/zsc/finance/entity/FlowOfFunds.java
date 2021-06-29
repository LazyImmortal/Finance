package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class FlowOfFunds {
    private Integer id;

    private Integer userid;

    private User user;

    private BigDecimal flowmoney;

    private Integer type;

    private String source;

    private Date createtime;

    private String funddesc;
}