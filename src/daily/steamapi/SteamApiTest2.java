package daily.steamapi;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangxc27652
 * @date 2020/9/15
 * @description steam测试  实例参考  https://developer.ibm.com/zh/articles/j-lo-java8streamapi/
 */
public class SteamApiTest2 {

    public static void main(String[] args) {

        // map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());

        System.out.println(squareNums);

        // filter 对原始 Stream 进行某项过滤测试，通过测试的元素被留下来生成一个新 Stream
        List<Integer> squareNums2 = nums.stream().filter(n-> n>2).collect(Collectors.toList());
        System.out.println(squareNums2);


        // jdk8中使用了::的用法。就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下，
        // 双冒号运算就是Java中的[方法引用],[方法引用]的格式是：类名 ：：方法名
        // 参考 www.cnblogs.com/maohuidong/p/11527681.html
        nums.forEach(System.out::println);
    }
}
