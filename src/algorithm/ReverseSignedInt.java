package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/23
 * @description  7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 输入: 123
 * 输出: 321
 * 输入: -123
 * 输出: -321
 * 输入: 120
 * 输出: 21
 * https://leetcode-cn.com/problems/reverse-integer/solution/tu-wen-xiang-jie-javadai-ma-de-2chong-shi-xian-fan/
 */
public class ReverseSignedInt {

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int t = x % 10;
            int newRes = res * 10 + t;
            //如果数字溢出，直接返回0
            if ((newRes - t) / 10 != res) {
                return 0;
            }
            res = newRes;
            x = x / 10;
        }
        return res;
    }

    /**
     * 方法2
     * https://leetcode-cn.com/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode/
     */
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }


}
