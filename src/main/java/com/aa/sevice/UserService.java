package com.aa.sevice;

import com.aa.domain.User;

/**
 * Created by 嘉宇 on 2020/4/4.
 */
public interface UserService {
    User findByName(String name);
    User findById(Integer id);
}
