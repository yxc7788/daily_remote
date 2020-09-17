package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/9/16
 * @description
 */
@Mapper
public interface UserDao {

    /**
     * 查找所有
     * @return
     */
    List<User> findAll();

    /**
     * 插入
     * @param user
     * @return
     */
    int insertUser(User user);
}
