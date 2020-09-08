package daily;

import java.util.concurrent.*;

/**
 * @author yangxc27652
 * @date 2020/9/8
 * @description  创建线程池，传入任务执行，可以传入Callable任务和Runable任务
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {

        // 创建定长线程池相关参数
        int maxValue = 10;
        BlockingQueue queue = new ArrayBlockingQueue(maxValue);
        ExecutorService service = new ThreadPoolExecutor(5,10,30,TimeUnit.SECONDS,queue);

        // 提交callable类型任务
        ThreadCallTask call = new ThreadCallTask();
        FutureTask futureTask = new FutureTask(call);
        service.submit(futureTask);
        Object a = futureTask.get();
        System.out.println(a);

        // 提交runable类型任务线程池提交任务30个,会报出异常
        for (int i=0;i<30;i++) {
            ThreadTask task = new ThreadTask(i);
            service.submit(task);
        }
    }
}


/**
 * Runnable的任务
 */
class ThreadTask implements Runnable{

    /**
     * 定义一个变量表示第几个任务
     */
    int i;
    ThreadTask(int i){
        this.i = i;
    }
    @Override
    public void run() {
        try {
            System.out.println("当前线程:" + Thread.currentThread().getName() + "正在执行第"+ i +"个任务");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "已经完成第"+ i +"个任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Callable的任务
 */
class ThreadCallTask implements Callable{

    @Override
    public Object call(){

        try {
            System.out.println("Callable的任务正在执行");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 就返回当前线程名字吧
        return Thread.currentThread().getName();
    }
}

