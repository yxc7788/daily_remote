package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/9/30
 * @description
 */
public class ArrayListTest {
    private static List list = new ArrayList();
    public static void main(String[] args) {
            for (int i = 0; i < 500; i++) {
                new Thread(() -> {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(Thread.currentThread().getName());}).start();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(list.size());
                    System.out.println(list);
                }
        }
}
