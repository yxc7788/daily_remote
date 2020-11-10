package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface User dao.
 *
 * @author yangxc27652
 * @date 2020 /9/16
 * @description
 */
@Mapper
public interface UserDao {

    /**
     * 查找所有
     *
     * @return list list
     */
    List<User> findAll();

    /**
     * 插入
     *
     * @param user the user
     * @return int int
     */
    int insertUser(User user);

    /**
     * Select user by id user.
     *
     * @param id the id
     * @return the user
     */
    User selectUserById(@Param("id") String id);

    /**
     * Update name by id int.
     *
     * @param id   the id
     * @param name the name
     * @return the int
     */
    int updateNameById(@Param("id") String id, @Param("name") String name);
}
