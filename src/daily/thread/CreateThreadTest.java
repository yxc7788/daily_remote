package daily.thread;

/**
 * @author yangxc27652
 * @date 2021/2/14
 * @description
 */
public class CreateThreadTest {

    public static void main(String[] args) {

        new Thread(){
            public void run(){
                System.out.println("thread1 start ... ");
            }
        }.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 start .... ");
            }
        }).start();


        //lambda的方式
        new Thread(()-> {
            for(int i = 1 ; i<10 ; i++){
                System.out.println("It is a lambda function!");
            }

        }).start();
    }
}
