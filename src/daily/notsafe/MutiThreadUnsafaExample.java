package daily.notsafe;

/**
 * @author yangxc27652
 * @date 2020/9/9
 * @description 多线程出现线程不安全的情况，没有加锁同步, 效果：存在两个线程有相同的num值,  而且43行没有if判断则最后num小于0
 *
 */

public class MutiThreadUnsafaExample {
    public static void main(String[] args) {
        // 发放100张票
        int num = 100;
        MyThread myThread = new MyThread(num);
        // MyThread myThread2 = new MyThread(num);    // 多个线程执行多个任务时候 每个MyThread对象都有自己的num变量，不会出现线程不安去
        // MyThread myThread3 = new MyThread(num);
        new Thread(myThread,"线程1").start();
        new Thread(myThread,"线程2").start();
        new Thread(myThread,"线程3").start();

    }
}

/**
 * 场景多个线程抢票
 */
class MyThread implements Runnable{

    static Object o = new Object();
    private int num;

    MyThread(int num){
        this.num = num;
    }
    @Override
    public void run() {

        try {
            while (num>0) {
                // Thread.sleep(100);    放在前面会出现结果为-2的情况，应该放在代码后面

               // synchronized (o){
                  //  if (num>0) {
                        num--;
                        System.out.println(Thread.currentThread().getName()+"抢到一张票，"+ "剩余数量为:"+ num);
                        Thread.sleep(100);
                   // }
              //  }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}