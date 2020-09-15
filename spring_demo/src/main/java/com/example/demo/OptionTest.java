package com.example.demo;

import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author yangxc27652
 * @date 2020/7/28
 * @description  option测试   https://www.runoob.com/java/java8-optional-class.html
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

        System.out.println("----------");

        // orElse()    https://www.jianshu.com/p/790f7c185d3e
        //当optional值不存在时，调用orElse()返回orElse()的参数，如果optional的值存在时返回optional的值
        String nullValue = null;
        String optional = Optional.ofNullable(nullValue).orElse("Su");
        System.out.println(optional);

        //orElseGet()
        //当optional值不存在时，调用orElseGet()中接口调用的返回值，如果optional的值存在时返回optional的值
        String optionalGet = Optional.ofNullable(nullValue).orElseGet(() -> "Xiao");
        System.out.println(optionalGet);

        String nonNullOptional = Optional.ofNullable("Susan").orElse("Su");
        System.out.println(nonNullOptional);

        String nonNullOptionalGet = Optional.ofNullable("Molly").orElseGet(() -> "Xiao");
        System.out.println(nonNullOptionalGet);
    }
}
