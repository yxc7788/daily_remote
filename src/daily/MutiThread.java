package daily;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author yangxc27652
 * @date 2020/9/7
 * @description  call方法实现多线程，
 */
public class MutiThread implements Callable {

    private int flag;
    // 构造方法（用于传参）
    MutiThread(int flag){
        this.flag = flag;
    }

    @Override
    public Object call() {
        System.out.println("进入多线程方法体"+ "threadtest" +flag);
        try {
            Thread.sleep(1000000);
            System.out.println("线程跑完结束" + "threadtest" + flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {

        for (int i=0;i< 10;i++){
            MutiThread call =  new MutiThread(i) ;
            FutureTask task = new FutureTask(call);
            new Thread(task,"threadtest"+ i).start();
            System.out.println("线程"+ i+ "开始执行");

        }
    }
}
