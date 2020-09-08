package daily;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @author yangxc27652
 * @date 2020/9/8
 * @description 生产者消费者模型
 */
public class ProductorAndConsumerTest {

    static int maxValue = 10;
    public static BlockingQueue queue = new ArrayBlockingQueue(maxValue);

    public static void main(String[] args) {

    }
}

class Goods {
    private BlockingQueue queue;
    // 当前数据
    private int num;
    // 最大数量
    private int maxValue;

    Goods (BlockingQueue queue, int maxValue){
        this.maxValue = maxValue;
        this.queue = queue;
    }

    public void put(){
        //queue.put("");
    }


}

/**
 * 消费者线程
 */
class ProductorThread implements Runnable{

    private BlockingQueue queue;
    private int maxValue;
    ProductorThread(BlockingQueue queue, int maxValue){
        this.maxValue = maxValue;
        this.queue = queue;
    }
    @Override
    public void run() {

    }
}

/**
 * 生产者线程
 */
class ConsumerThread implements Runnable{

    private BlockingQueue queue;
    private int maxValue;
    ConsumerThread(){
        this.maxValue = maxValue;
        this.queue = queue;
    }
    @Override
    public void run() {

    }
}