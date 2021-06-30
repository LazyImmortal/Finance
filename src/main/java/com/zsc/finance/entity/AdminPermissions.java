package com.zsc.finance.entity;

import lombok.Data;

@Data
public class AdminPermissions {
    private Integer id;

    private Integer adminid;

    private Admin admin;

    private Integer permissionid;

    private Permissions permissions;
}