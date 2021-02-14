package daily.thread;

import java.util.concurrent.*;

/**
 * @author yangxc27652
 * @date 2020/9/8
 * @description  创建线程池，传入任务执行，可以传入Callable任务和Runable任务
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {

        // 创建定长线程池相关参数
        int maxValue = 30;
        BlockingQueue queue = new ArrayBlockingQueue(maxValue);
        ExecutorService service = new ThreadPoolExecutor(5,10,30,TimeUnit.SECONDS,queue);

        // 提交callable类型任务
        ThreadCallTask call = new ThreadCallTask();
        FutureTask<String> futureTask = new FutureTask(call);
        service.submit(futureTask);
        String a = futureTask.get();
        System.out.println(a);

//        ThreadCallTask call = new ThreadCallTask();
//        Future<String> futureTask =  service.submit(call);

//        try {
//
//            Thread.sleep(90);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        try {
//            String a = futureTask.get();
//            System.out.println(a);
//        } catch (Exception e) {
//            System.out.println("捕获futureTask中的异常, 内容是 " + e.getMessage());
//        }






//        // 提交callable类型任务2
//        ThreadCallTask call1 = new ThreadCallTask();
//        FutureTask<String> futureTask1 = new FutureTask(call);
//        service.submit(futureTask1);
//        String a1 = futureTask1.get();
//        System.out.println(a1);

        // 提交runable类型任务线程池提交任务30个,会报出异常
//        for (int i=0;i<30;i++) {
//            ThreadCallTask task = new ThreadCallTask();
//            service.submit(task);
//        }
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
            System.out.println("Callable的任务正在执行,线程为：" + Thread.currentThread().getName());
            Thread.sleep(3000);
            //throw new RuntimeException("抛出一个异常");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 就返回当前线程名字吧
        return Thread.currentThread().getName() + "222";
    }
}

