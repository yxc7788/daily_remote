package algorithm;

import java.util.Arrays;

/**
 * @author yangxc27652
 * @date 2021/1/14
 * @description  31. 下一个排列
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 输入：nums = [1]
 * 输出：[1]
 * https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 */
public class NextPermutation {
    /**
     * 我们希望下一个数比当前数大，这样才满足“下一个排列”的定义。因此只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。
     * 比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
     * 我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
     * 在尽可能靠右的低位进行交换，需要从后向前查找
     * 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     * 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。
     * 以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。
     * 显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     * 以上就是求“下一个排列”的分析过程。
     * 算法过程
     *
     * 标准的“下一个排列”算法可以描述为：
     * 从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
     * 在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
     * 将 A[i] 与 A[k] 交换
     * 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
     * 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return ; //特判
        }
        int min = n-1; //较大值中的最小值的下标初始化为n-1
        boolean flag = false; //用于判断是不是递减序列
        for (int i = n -1; i >0 ; i--) {
            if (nums[i] > nums[i-1]) { //从后往前遍历，遇到更小数就开始下一步
                while (nums[i-1]>=nums[min]){//while循环用于找到较大数中的最小数所在的索引min
                    min--;
                }
                change(nums,i-1,min);//先交换，交换之后索引大于i-1的元素一定是降序的。
                reverse(nums,i);//再反转（从i开始）
                flag = true;//有交换就不是递减数组
                break;
            }
        }
        if (!flag) {
            Arrays.sort(nums); //flag没变说明是递减数组，那就递增排nums
        }
    }

    /**
     * 交换位置i和j的值
     */
    public void change(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    /**
     * 反转nums中start位置到最后部分
     */
    public void reverse(int[] nums, int start){
        int n = nums.length;
        int end = n-1;
        while (end > start){
            change(nums,start,end);
            end--;start++;
        }
    }

}
