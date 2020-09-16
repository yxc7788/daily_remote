package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangxc27652
 * @date 2020/9/16
 * @description
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ValueOperations valueOperations;

    @Autowired
    private HashOperations hashOperations;

    @RequestMapping("/test")
    public void test() {
        redisTemplate.opsForValue().set("lonely", "i miss you");
        String uu = stringRedisTemplate.opsForValue().get("lonely");

        // 常用key - value
        stringRedisTemplate.opsForValue().set("uu", "uzi");
        String zz = stringRedisTemplate.opsForValue().get("uu");
        System.out.println("String为"+ zz);



        // list
        System.out.println("---------------");
        List<String> list = new ArrayList<String>();
        list.add("1111");
        list.add("222");
        stringRedisTemplate.opsForList().rightPushAll("list11", list);
        stringRedisTemplate.opsForList().getOperations();

        redisTemplate.opsForList().leftPush("list11","a");
        redisTemplate.opsForList().rightPush("list11","b");

        List list1 = redisTemplate.opsForList().range("list11",0,-1);
        System.out.println("List为:" + list1);
        String leftPop =  (String) redisTemplate.opsForList().leftPop("list11");
        System.out.println("从左侧弹出了："+ leftPop);

        // hash的操作
        System.out.println("---------------");
        redisTemplate.opsForHash().put("person","name","ligang");
        redisTemplate.opsForHash().put("person","age","31");
        redisTemplate.opsForHash().put("person","slary","1000");
        // 获取变量中所有键值对
        Map map = redisTemplate.opsForHash().entries("Person");
        System.out.println(map);


        valueOperations.set("xiyang","i love you");


    }
}
