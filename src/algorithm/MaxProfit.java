package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description
 *  * [7, 1, 5, 3, 6, 4]  只能买一次
 *  * 时间复杂度：O(n)O(n)，只需要遍历一次。
 *  * 空间复杂度：O(1)O(1)，只使用了常数个变量。
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
 */

public class MaxProfit {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

}
