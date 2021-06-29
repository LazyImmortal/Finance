package com.zsc.finance.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String realname;

    private String password;

    private String idcard;

    private String phone;

    private String email;

    private Integer paypwd;

    private Integer status;

    private String reputation;
}