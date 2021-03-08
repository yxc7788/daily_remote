package daily;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yangxc27652
 * @date 2021/2/12
 * @description 生产者消费者模型，（没有共享变量，不需要线程安全同步）
 */
public class CustomerAndProductor {


    /**
     * 方法1：主要原理利用阻塞队列的put和take实现两种情况的阻塞
     */
    private static final int MAX_NUM = 10;
    private static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>(MAX_NUM);

    public static void test0 () {
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
        test0();
        

    }
}

