package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 动态规划 时间复杂度：O(n)  空间复杂度：O(1)
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
 */
public class MaxProfit2 {

    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

}
