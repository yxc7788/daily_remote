package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/30
 * @description
 * 0-4随机数：0，1，2，3，4. 共计5个数字。
 * 0-6随机数：0，1，2，3，4，5，6.共计7个数字。
 * 如何用rand4() 能生成一个 rand6()呢？
 *
 * https://blog.csdn.net/liucoding/article/details/106178757
 */
public class Random0To6 {
    public static int rand4() {
        //大于等于 0.0 且小于 1.0
        double rand = Math.random() * 5;
        return (int) rand;
    }

    /**
     * 0 -6 实际 7个数字
     * @return
     */
    public static int rand6() {
        int result = rand4() * 5 + rand4();
        do {
            result = rand4() * 5 + rand4();
        } while (result > 20);
        return result / 3;
    }

    public static void main(String[] args) {
        int[] result = new int[7];
        for (int i = 0; i < 50000; i++) {
            int r = rand6();
            result[r]++;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println("num:" + i + " times: " + result[i]);
        }
    }
}
