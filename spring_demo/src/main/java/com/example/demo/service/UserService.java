package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/9/16
 * @description
 */
public interface UserService  {
    /**
     * 找出所有记录
     * @return
     */
    List<User> findAll();
}
