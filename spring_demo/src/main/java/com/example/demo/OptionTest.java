package com.example.demo;

import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author yangxc27652
 * @date 2020/7/28
 * @description  option测试
 */
public class OptionTest {
    public static void main(String[] args) {
        Optional<String> opt = Optional.of("binghe");

        System.out.println(opt.isPresent());
        opt.ifPresent(name -> System.out.println(name.length()));

        Optional<String> opt1 = Optional.ofNullable(null);
        System.out.println(opt1);
        System.out.println(opt1.isPresent());

        Object o1 = "";
        Object o2 = null;
        System.out.println(ObjectUtils.nullSafeEquals(o1,o2));
    }
}
