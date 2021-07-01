package com.zsc.finance.service.impl;

import com.zsc.finance.entity.User;
import com.zsc.finance.entity.UserExample;
import com.zsc.finance.mapper.UserMapper;
import com.zsc.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User selectUserByUsername(String username, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (username != null){
            criteria.andUsernameEqualTo(username);
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
//    @CachePut(cacheNames = "user", key = "#user.id")
    public Integer updateUser(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        System.out.println("  :" + user.toString());
        redisTemplate.opsForValue().set("user_" + user.getId(), user);
        return result;
    }

    @Override
    @Transactional
    public Integer insertUser(User user) {
        int result = userMapper.insertSelective(user);
        return result;
    }

    @Override
//    @Cacheable(cacheNames = "user", unless = "#result==null")
    public User selectUserById(Integer id) {
        Object object = redisTemplate.opsForValue().get("user_" + id);
        if (object != null) {
            return (User)object;
        }
        else {
            User user = userMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set("user_" + id, user, 1, TimeUnit.DAYS);
            return user;
        }
    }

    @Override
    @Transactional
//    @CacheEvict(cacheNames = "user")
    public Integer deleteUserById(Integer id) {
        redisTemplate.delete("user_" + id);
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer updateUserPassword(String username, String newPassword) {
        User user = userMapper.selectByUsername(username);
        System.out.println("UserServiceImpl 旧密码:" + user.getPassword());
        user.setPassword(newPassword);
        System.out.println("UserServiceImpl 新密码:" + user.getPassword());
        return this.updateUser(user);
    }
}
