package com.zsc.finance.entity;

import lombok.Data;

@Data
public class UserPermissions {
    private Integer id;

    private Integer userid;

    private User user;

    private Integer permissionid;

    private Permissions permissions;
}