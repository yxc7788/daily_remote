package algorithm;

import java.util.Arrays;

/**
 * @author yangxc27652
 * @date 2021/1/11
 * @description 217. 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * 输入: [1,2,3,1]
 * 输出: true
 * 输入: [1,2,3,4]
 * 输出: false
 *
 */
public class ContainsDuplicate {

    /**
     * 时间复杂度nlogn
     * 空间 logn
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }


}
