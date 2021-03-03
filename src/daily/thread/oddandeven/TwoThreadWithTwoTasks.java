package daily.thread.oddandeven;

/**
 * @author yangxc27652
 * @date 2021/3/1
 * @description
 */
public class TwoThreadWithTwoTasks {

    public static int i = 0;
    public static int j = 0;
    static Object block = new Object();
    static boolean flag = false;

    public static void test0 (){
        new Thread(() -> {
            while (i < 10) {
                synchronized (block) {
                    if (flag) {
                        try {
                            block.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()
                                + "----" + (i++));
                        flag = true;
                        block.notifyAll();
                    }

                }
            }
        }).start();

        new Thread(() -> {
            while (i < 10) {
                synchronized (block) {

                    if (!flag) {
                        try {
                            block.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()
                                + "----" + (i++));
                        flag = false;
                        block.notifyAll();
                    }

                }
            }
        }).start();
    }


    public static void test2 () {
        new Thread(() -> {
            while (j < 10) {
                synchronized (block) {
                    if (j % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "-" + (j++));
                        block.notifyAll();
                    } else {
                        try {
                            block.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

        new Thread(() -> {
            while (i < 10) {
                synchronized (block) {
                    if (j % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + "-" + (j++));
                        block.notifyAll();
                    } else {
                        try {
                            block.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();
    }

    public static void main(String[] args) {

        //test0();
        test2();
    }
}

