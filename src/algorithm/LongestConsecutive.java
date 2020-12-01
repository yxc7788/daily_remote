package algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {

        /**
         * 我们要枚举的数 x 一定是在数组中不存在前驱数 x-1 的，
         * 不然按照上面的分析我们会从 x-1 开始尝试匹配，
         * 因此我们每次在哈希表中检查是否存在 x-1即能判断是否需要跳过了。
         */
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
