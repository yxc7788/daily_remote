package daily.steamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yangxc27652
 * @date 2020/9/15
 * @description  steam流测试
 */
public class SteamApiTest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("java");

        list.add("php");

        list.add("python");

        Stream<Integer> stream = list.stream().map(p -> p.length());

        Integer[] lengthArr = stream.toArray(Integer[]:: new);

        for (int a: lengthArr) {
            System.out.println(a);
        }
    }
}
