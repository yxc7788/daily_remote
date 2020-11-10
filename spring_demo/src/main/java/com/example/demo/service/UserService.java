package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * The interface User service.
 *
 * @author yangxc27652
 * @date 2020 /9/16
 * @description
 */
public interface UserService  {
    /**
     * 找出所有记录
     *
     * @return list list
     */
    List<User> findAll();

    /**
     * Select user by id user.
     *
     * @param id the id
     * @return the user
     */
    User selectUserById(String id);

    /**
     * 插入
     *
     * @param user the user
     * @return integer integer
     */
    Integer insertUser(User user);

    /**
     * Update name by id.
     *
     * @param id   the id
     * @param name the name
     */
    void updateNameById(String id,String name);
}
