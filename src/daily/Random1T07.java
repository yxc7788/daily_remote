package daily;

/**
 * @author yangxc27652
 * @date 2020/12/30
 * @description 给定一个函数rand5()，该函数可以随机生成1-5的整数，且生成概率一样。
 * 现要求使用该函数构造函数rand7()，使函数rand7()可以随机等概率的生成1-7的整数。
 */
public class Random1T07 {

    public static int rand5() {

        int num =  (int)(Math.random()*(5) + 1);
        return num;
    }

    /**
     * rand5()函数生成1-25之间的数字，然后将其中的1-21映射成1-7，
     * 丢弃22-25。例如生成(1，1)，(1，2)，(1，3)，则看成rand7()中的1，
     * 如果出现剩下的4种，则丢弃重新生成。
     * @return
     */
    public static int  rand7() {
        int x = 22;
        while (x > 21) {
            x = rand5() + (rand5() - 1)*5;
        }
        return 1 + x%7;
    }

    public static void main(String[] args) {
        int [] count = new int [8];
        for (int i = 0; i<1000000; i ++) {
            count[rand7()] ++ ;
        }
        for (int j : count) {
            System.out.println(j);
        }

    }
}
