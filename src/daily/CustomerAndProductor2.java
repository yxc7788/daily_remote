package daily;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yangxc27652
 * @date 2021/2/12
 * @description 生产者消费者模型, 原理利用synchnized + wait notify
 */
public class CustomerAndProductor2 {

    private int MAX;
    private int cnt = 0;

    public CustomerAndProductor2 (int MAX) {
        this.MAX = MAX;
    }

    public synchronized void produce() throws InterruptedException {
        while (cnt == MAX) {
            System.out.println("元素个数已满，生产者阻塞");
            wait();
        }
        System.out.println("生产者生产，元素个数为:" + ++cnt);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (cnt == 0) {
            System.out.println("元素个数已空，消费者阻塞");
            wait();
        }
        System.out.println("消费者消费，元素个数为:" + --cnt);
        notifyAll();
    }

    public static void main(String[] args) {
        CustomerAndProductor2 pool = new CustomerAndProductor2(6);
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        pool.produce();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        pool.consume();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

