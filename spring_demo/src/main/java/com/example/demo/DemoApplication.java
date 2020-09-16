package com.example.demo;

import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Demo application.
 *
 */
@RestController
@SpringBootApplication
@MapperScan("com.example.demo.dao")//使用MapperScan批量扫描所有的Mapper接口；
public class DemoApplication {

    @Autowired
    private UserServiceImpl userService;
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Test string.
     * @author
     * @return the string
     * @date
     */
    @RequestMapping("/test")
    @ResponseBody()
    public String test() {
        System.out.println(userService.findAll().size());
        return "haha";
    }
}
