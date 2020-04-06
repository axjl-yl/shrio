package com.aa.dao;

import com.aa.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 嘉宇 on 2020/4/4.
 */
@Mapper
public interface UserDao {
    User findByName(String name);
    User findById(Integer  id);
}
