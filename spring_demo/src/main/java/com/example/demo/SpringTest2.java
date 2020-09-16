package com.example.demo;

import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangxc27652
 * @date 2020/9/15
 * @description  为什么会注入失败
 */
public class SpringTest2 {
    @Autowired
    SpringTest springTest;

    public static void main(String[] args) {
        new SpringTest2().b();
    }
    public void b(){
        springTest.a();
    }
}

