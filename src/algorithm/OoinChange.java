package algorithm;

import java.util.Arrays;

/**
 * @author yangxc27652
 * @date 2020/12/28
 * @description   322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 输入：coins = [1], amount = 2
 * 输出：2
 * https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
 */
public class OoinChange {
    public int coinChange1(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 方法2
     */
    public int coinChange(int[] coins, int amount) {
        // 初始条件检查
        if (amount < 1) {
            return 0;
        }
        // 动态规划入口
        return coinChange(coins, amount, new int[amount]);
    }

    /**
     * 自上而下的动态规划方法
     * coins:硬币面额
     * rem:余额
     * count:存储中间计算结果，空间换时间
     */
    private int coinChange(int[] coins, int rem, int[] count) {
        // 结束条件：此路径不通
        if (rem < 0) {
            return -1;
        }

        // 结束条件：余额为0，成功结束
        if (rem == 0) {
            return 0;
        }
        // 之前已经计算过这种情况，直接返回结果，避免重复计算
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        // 遍历当前递归子树的每一种情况
        for (int coin : coins) {
            // 用一下coin这个面值的硬币会怎样？res是这个方法的最优情况
            int res = coinChange(coins, rem - coin, count);
            // res<0 即为 res=-1,此法失败，res>min不是最优情况，舍去
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        // count[rem - 1]存储着给定金额amount的解
        // 若为Integer.MAX_VALUE则该情况无解
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

}
