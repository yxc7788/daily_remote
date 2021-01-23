package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2021/1/14
 * @description  448. 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [5,6]
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/zhao-dao-suo-you-shu-zu-zhong-xiao-shi-de-shu-zi-2/
 */

public class FindDisappearedNumbers {

    public List<Integer> findDisappearedNumbers0(int[] nums) {

        /**
         * 原地修改
         * 我们需要知道数组中存在的数字，由于数组的元素取值范围是 [1, N]，所以我们可以不使用额外的空间去解决它。
         * 我们可以在输入数组本身以某种方式标记已访问过的数字，然后再找到缺失的数字。
         * 算法：
         * 遍历输入数组的每个元素一次。
         * 我们将把 |nums[i]|-1 索引位置的元素标记为负数。即 nums[∣nums[i]∣−1]×−1 。
         * 然后遍历数组，若当前数组元素 nums[i] 为负数，说明我们在数组中存在数字 i+1。
         */
        for (int i = 0; i < nums.length; i++) {
            int newIndex = Math.abs(nums[i]) - 1;
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }
        List<Integer> result = new LinkedList<Integer>();
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }
        return result;
    }




    /**
     * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/bu-xu-yao-e-wai-kong-jian-si-lu-chao-ji-qing-xi-bu/
     * 根据题目特点，可以把数组中的元素与索引建立一一对应的关系。因为索引是确定的0到n-1,一个也不缺，而数组的元素不确定，少了哪个也不知道。
     * 既然两者是一一对应的关系，那么我们对数组中的每个元素对应的索引做个标记；
     * 然后再对索引进行一次遍历，那么不存的元素就不会对它对应的索引进行比较，由此可查找出这些不存在的元素。
     * 思路
     *
     * 遍历每个元素，对索引进行标记
     * 将对应索引位置的值变为负数；
     * 遍历下索引，看看哪些索引位置上的数不是负数的。
     * 位置上不是负数的索引，对应的元素就是不存在的。
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //用来存放结果
        List<Integer> res = new ArrayList<>();
        //1. 遍历下数组的元素，对对应的索引位置的元素作标记
        int len = nums.length;
        for (int i = 0; i < len; i++){
            int num = Math.abs(nums[i]);  //由于数组的元素有可能被*-1，所以取绝对值
            int index = num - 1;
            if (nums[index] > 0){
                nums[index] *= -1;
            }
        }
        // 寻找没有标记的索引位置
        for (int i = 0; i < len; i++){
            if (nums[i] > 0){
                int num = i + 1;  //将索引转化为对应的元素
                res.add(num);
            }
        }
        return res;
    }


}
