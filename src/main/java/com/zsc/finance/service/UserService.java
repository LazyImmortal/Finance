package com.zsc.finance.service;

import com.zsc.finance.entity.User;

import java.util.List;

public interface UserService {

    User selectUserByUsername(String username, String password);

    User selectUserByEmail(String email, String password);

    List<User> selectUserByStatusDesc();

    List<User> selectAllUser();

    Integer updateUser(User user);

    Integer insertUser(User user);

    User selectUserById(Integer id);

    Integer deleteUserById(Integer id);
}
