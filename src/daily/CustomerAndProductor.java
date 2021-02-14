package daily;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yangxc27652
 * @date 2021/2/12
 * @description
 */
public class CustomerAndProductor {


    private static final int MAX_NUM = 10;
    private static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>(MAX_NUM);

    public void produce(String str) {
        try {
            QUEUE.put(str);
            if (QUEUE.size() == MAX_NUM) {
                System.out.println("队列已满");
            }
            System.out.println("队列放入元素" + str + "," + " 队列元素数量:" + QUEUE.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String consume() {
        String str = null;
        try {
            str = QUEUE.take();
            if (QUEUE.size() == 0) {
                System.out.println("队列已空");
            }
            System.out.println("队列移出元素:" + str +  "," + " 队列元素数量:" + QUEUE.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        CustomerAndProductor queueTest = new CustomerAndProductor();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                String str = "元素-";
                while (true) {
                    queueTest.produce(str + finalI);
                }
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    queueTest.consume();
                }
            }).start();
        }
    }
}

