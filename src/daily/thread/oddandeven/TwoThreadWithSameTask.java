package daily.thread.oddandeven;

/**
 * @author yangxc27652
 * @date 2021/3/1
 * @description
 */
public class TwoThreadWithSameTask {

    private static int count = 0;
    private final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new TurningRunner(), "偶数");
        Thread b = new Thread(new TurningRunner(), "奇数");
        a.start();
        b.start();
    }

    /**
     * 这个类一定是静态内部类
     */
    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 10) {
                // 获取锁
                synchronized (lock) {
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    // 唤醒其他线程
                    lock.notifyAll();
                    try {
                        // 如果任务还没有结束，则让出当前的锁并休眠
                        if (count <= 10) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}



