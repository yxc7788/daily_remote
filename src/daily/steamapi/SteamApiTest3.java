package daily.steamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangxc27652
 * @date 2020/9/29
 * @description 流去重测试 (注意流操作一定要赋给一个引用)
 */
public class SteamApiTest3 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a","a","a","a","b","b","b");
        // 这种方式不对  假如想返回一个去重得流，不能这么用
        list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);

        // 必须将返回结果赋给一个引用
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);


        ArrayList<String> users = new ArrayList<>();
        users.add("c");
        users.add("c");
        users.add("c");
        users.add("c");
        users.add("d");
        users.add("d");
        users.add("d");
        users.add("d");
        users.add("d");
        users.add("d");
        List<String> users1 = users.stream().distinct().collect(Collectors.toList());
        // 这样用流，筛选出来得元素进行某种操作
        // 最后输出得还是10个
        System.out.println(users1);



        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);
        List<Integer> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates);



    }
}
