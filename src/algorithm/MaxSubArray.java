package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }


    public int maxSubArray1(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num: nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 自己的，如果是负数是不是有问题
     */
    public int maxSubArray2(int[] nums) {

        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0 ; i < nums.length ; i++) {
            currentSum  = nums[i] + currentSum;
            if (currentSum < 0) {
                currentSum = 0;
            }
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

}
