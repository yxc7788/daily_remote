package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxc27652
 * @date 2020/11/3
 * @description  arthas 的trace命令测试   test方法中递归调用 abcd
 */

// 首先要找到java进程号
//arthas 使用方式： 管理员方式进入命令行 cmd  ，然后cd  进入arthas路径，  执行： （.\as.bat  pid）     进入atrhas 命令行
// trace命令的格式为    trace com.example.demo.controller.ArthasController  test (注意这里是是类的全限定名 中间用.)

@RestController
@RequestMapping("arthas")
public class ArthasController {
    /**
     *  查找路径时候选择类右键copy —— path from source root
     */


    @Autowired
    private UserDao userDao;

    @ResponseBody
    @RequestMapping("/trace")
    public String test() {
        userDao.findAll();
        a();
        b();
        c();
        d();
        return "返回";
    }

    private void a() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b();
    }

    private void b() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c();
    }
    private void c() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d();
    }

    private void d() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
