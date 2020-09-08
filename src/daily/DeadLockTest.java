package daily;

/**
 * @author yangxc27652
 * @date 2020/9/8
 * @description  死锁测试
 */
public class DeadLockTest {

    public static void main(String[] args) {

        // 创建两个锁对象
        Object a = new Object();
        Object b = new Object();
        // 开启两个线程
        new Thread(new ThreadTask1(a,b)).start();
        new Thread(new ThreadTask2(a,b)).start();

    }

}

class ThreadTask1 implements Runnable{
    Object a;
    Object b;

    ThreadTask1(Object a, Object b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public void run(){
        // 先获得a对象，然后获得b
        try {
            synchronized (a){
                System.out.println("线程:" +Thread.currentThread().getName() + "获得锁对象a");
                Thread.sleep(1000);
                synchronized (b){
                    System.out.println("线程:" +Thread.currentThread().getName() + "获得锁对象b");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadTask2 implements Runnable{

    Object a;
    Object b;
    ThreadTask2(Object a, Object b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public void run(){
        //先获得b对象，然后获得a
        try {
            synchronized (b){
                System.out.println("线程:" +Thread.currentThread().getName() + "获得锁对象b");
                Thread.sleep(1000);
                synchronized (a){
                    System.out.println("线程:" +Thread.currentThread().getName() + "获得锁对象a");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}