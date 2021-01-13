package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description No11.盛水最多容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 输入：height = [1,1] 输出：1  输入：height = [4,3,2,1,4]输出：16
 * https://leetcode-cn.com/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
 */
public class MaxArea {


    /**
     * self
     */
    public int maxArea(int[] height) {
        // int[] dp = new int[height];
        if (height.length == 0) {
            return 0;
        }
        int res  = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int cur = Math.min(height[left], height[right]) * (right - left);
            if (height[left] < height[right]) {
                left ++;
            }
            else {
                right--;
            }
            res = Math.max(cur, res);
        }
        return res;
    }
    /**
     * 不断向内收短板
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

}
