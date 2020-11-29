package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/9/16
 * @description
 */

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/test")
    public void transactionTest() {
        User user = userService.selectUserById("3");
        System.out.println("id是3的用户，名字是" + user.getName());

        userService.updateNameById("3", "222");
        // User userNew = userService.selectUserById("3");
        User userNew = selectUser();
        System.out.println("更新之后，id是3的用户，名字是" + userNew.getName());

        updateName("2", "刘备");
        System.out.println("第二次更新之后，id是2的用户，名字是" + userService.selectUserById("3").getName());

        throw new RuntimeException("抛出一个异常，让事务回滚");
    }

    private void updateName(String id, String name) {
        userService.updateNameById(id, name);
    }
    private User selectUser() {
        return userService.selectUserById("3");
    }
}
