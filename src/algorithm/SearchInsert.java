package algorithm;

/**
 * @author yangxc27652
 * @date 2021/1/4
 * @description  35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * https://leetcode-cn.com/problems/search-insert-position/solution/sou-suo-cha-ru-wei-zhi-by-leetcode-solution/
 */
public class SearchInsert {

    /**
     * 2
     * 和二分法大体一致，只是返回值为left （二分法返回值是 -1）
     */
    public int searchInsert1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 和二分查找的区别就是这个返回值不同。
        return left;
    }



    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1, ans = n;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
