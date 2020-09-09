package daily.notsafe;

/**
 * @author yangxc27652
 * @date 2020/9/9
 * @description 多线程出现线程不安全的情况，多线程 i++ 1000次，最后结果小于1000
 *
 */

public class MutiThreadUnsafaExample2 {
    public static void main(String[] args) {

        MyThread2 myThread = new MyThread2();
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
class MyThread2 implements Runnable{

    static Object o = new Object();
    int num;
    @Override
    public void run() {

        try {
            // 这样写的话，不能实现线程之间共享变量了，因为这个i是线程私有的变量，也就是每个线程有自己的值，
//            for (int i=0;i<=1000;i++){
//                System.out.println(Thread.currentThread().getName()+"正在执行，结果是:"+ i);
//            }
            for (num = 0;num<1000;num++){
                System.out.println(Thread.currentThread().getName()+"正在执行，结果是:"+ num);
            }
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}