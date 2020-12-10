package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 二分查找
 */
public class BinarySearch {

    public int search(int[] nums, int target) {

        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (nums[pivot] == target) {
                return pivot;
            }
            if (target < nums[pivot]) {
                right = pivot - 1;
            }
            else {
                left = pivot + 1;
            }
        }
        return -1;
    }
}
