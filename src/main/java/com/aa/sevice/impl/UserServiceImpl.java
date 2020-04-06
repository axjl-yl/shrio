package com.aa.sevice.impl;

import com.aa.dao.UserDao;
import com.aa.domain.User;
import com.aa.sevice.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 嘉宇 on 2020/4/4.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
