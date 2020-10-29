package daily.thread;

/**
 * @author yangxc27652
 * @date 2020/10/29
 * @description   线程A和线程B存储在ThreadLocal中的变量互不干扰，线程A存储的变量
 * 只能由线程A访问，线程B存储的变量只能由线程B访问
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    public static void main(String[] args){
    //创建第一个线程
        Thread threadA = new Thread(()->{
            threadLocal.set("ThreadA：" + Thread.currentThread().getName());
            System.out.println("线程A本地变量中的值为：" + threadLocal.get());
            threadLocal.remove();
            System.out.println("线程A删除本地变量后ThreadLocal中的值为：" +
                    threadLocal.get());
        });
        //创建第二个线程
        Thread threadB = new Thread(()->{
            threadLocal.set("ThreadB：" + Thread.currentThread().getName());
            System.out.println("线程B本地变量中的值为：" + threadLocal.get());
            System.out.println("线程B没有删除本地变量：" + threadLocal.get());
        });
        //启动线程A和线程B
        threadA.start();
        threadB.start();
    }
}
