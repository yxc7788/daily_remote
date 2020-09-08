package daily;

import java.util.Random;

/**
 * @author yangxc27652
 * @date 2020/9/8
 * @description 随机数生成
 */
public class RandomGenerator {
    public static void main(String[] args) {

        // 输出0到1之间得数，默认的double类型
        for (int i = 0;i < 5;i++) {
            double num = Math.random();
            System.out.println("随机数方法1 : "+ num);
        }

        //   注意 Math.random() 这个方法返回是double类型，注意取值的范围[0,1)（能够取到零值，注意除零异常），
        //   如果想获取整数类型的随机数，不要将x放大10的若干倍然后取整，直接使用Random对象的nextInt或者nextLong方法。
        for (int i = 0;i < 5;i++) {
            Random random = new Random();
            // 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n
            int num = random.nextInt(10);
            System.out.println("随机数方法2 : "+ num);
        }

        // 这种方法也可以返回一个[m,n)之间的随机数整数
        for (int i = 0;i < 5;i++) {
            int m = 5;
            int n = 10;
            int  num = (int)(Math.random()*(n-m)+m);
            System.out.println("随机数方法3 : "+ num);
        }

    }
}
