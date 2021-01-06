package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/22
 * @description 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]。
 * https://leetcode-cn.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     *  方法2
     */
    public void moveZeroes2(int[] nums) {
        int indexNow = 0;
        int indexNum = 0;
        int m = nums.length;

        while (indexNum<m){
            if (nums[indexNum] != 0) {
                nums[indexNow++] = nums[indexNum];
            }
            ++indexNum;
        }

        for (int i = indexNow; i < m; i++){
            nums[i] = 0;
        }
    }

}
