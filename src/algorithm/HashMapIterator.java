package algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yangxc27652
 * @date 2021/1/25
 * @description
 */
public class HashMapIterator {

    public static void main(String[] args) {


        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        /**
         *         第一种：遍历HashMap的entrySet键值对集合
         *         1.通过HashMap.entrySet()得到键值对集合；
         *         2.通过迭代器Iterator遍历键值对集合得到key值和value值；
         */
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            System.out.println("key=" + m.getKey() + ","
                    + "value=" + m.getValue());
        }
        System.out.println();
        // 第二种：采用 foreach 遍历 Map.entryset
        for (Map.Entry<String, String> m : map.entrySet()) {
            System.out.println("key=" + m.getKey() + "," +
                    "value=" + m.getValue());
        }
        System.out.println();

        /**
         * 第三种：遍历HashMap“值”的集合；
         * 1.通过HashMap.values()得到“值”的集合
         * 2.遍历“值”的集合；
         */

        for (String value : map.values()) {
            System.out.println("value: " + value);
        }
        System.out.println();

        /**
         *  第四种（恒生规范不推荐）：遍历HashMap键的Set集合获取值；
         *  1.通过HashMap.keySet()获得键的Set集合；
         *  2.遍历键的Set集合获取值；
         */
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println("key=" + key + "," + "value=" + map.get(key));
        }
    }
}
