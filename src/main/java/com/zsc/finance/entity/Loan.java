package com.zsc.finance.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Loan {
    private Integer id;

    private Integer loanid;

    private User user;

    private Integer examineid;

    private Admin admin;

    private Date loantime;

    private BigDecimal amount;

    private Integer term;

    private BigDecimal rate;

    private Integer applystatus;

    private Integer loanstatus;
}