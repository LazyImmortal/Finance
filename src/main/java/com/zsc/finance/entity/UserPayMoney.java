package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class UserPayMoney {
    private Integer id;

    private Integer userid;

    private User user;

    private Integer payid;

    private  PayMoney payMoney;

    private Date starttime;

    private BigDecimal averyield;

    private BigDecimal profit;

    private Integer status;
}