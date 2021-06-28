package com.zsc.finance.service.impl;

import com.zsc.finance.entity.User;
import com.zsc.finance.entity.UserExample;
import com.zsc.finance.mapper.UserMapper;
import com.zsc.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    @Cacheable(cacheNames = "user", unless = "#result==null")
    public User selectUserByUsername(String username, String password) {
        UserExample userExample =new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (username!=null){
            criteria.andUsernameEqualTo(username);
        }
        if (password!=null){
            criteria.andPasswordEqualTo(password);
        }
        List<User> list = userMapper.selectByExample(userExample);
        if ("[]".equals(list.toString())){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    @Cacheable(cacheNames = "user", unless = "#result==null")
    public User selectUserByEmail(String email, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (email != null){
            criteria.andEmailEqualTo(email);
        }
        if (password != null){
            criteria.andPasswordEqualTo(password);
        }
        List<User> list = userMapper.selectByExample(userExample);
        if ("[]".equals(list.toString())){
            return null;
        }else {
            return list.get(0);
        }
    }

    @Override
    public List<User> selectUserByStatusDesc() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("status desc");
        return userMapper.selectByExample(userExample);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectByExample(null);
    }

    @Override
    @Transactional
    public Integer updateUser(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        return result;
    }

    @Override
    @Transactional
    public Integer insertUser(User user) {
        int result = userMapper.insertSelective(user);
        return result;
    }

    @Override
    @Cacheable(cacheNames = "user", unless = "#result==null")
    public User selectUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    @Transactional
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
