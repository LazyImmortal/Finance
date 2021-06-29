package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class UserTermFinancial {
    private Integer id;

    private Integer userid;

    private User user;

    private Integer termid;

    private TermFinancial termFinancial;

    private Date starttime;

    private BigDecimal averyield;

    private BigDecimal profit;

    private Integer status;
}