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

        System.out.println("----------对象去重-------");
        List<User> userList = new ArrayList<User>();
        userList.add(new User("11"));
        userList.add(new User("11"));
        userList.add(new User("12"));
        userList.add(new User("12"));

        // 这种方式对于对象没有效果
        userList = userList.stream().distinct().collect(Collectors.toList());
        System.out.println(userList);

        List<String> ids = new ArrayList<>();
        userList = userList.stream().filter(
                v -> {
                    boolean flag = !ids.contains(v.getId());
                    ids.add(v.getId());
                    return flag;
                }
        ).collect(Collectors.toList());

        System.out.println(userList);
    }
}


class User {

    String id;

    User(String id){
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
