package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/12/24
 * @description 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
 */
public class Subsets {

    /**
     * 方法1
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int num : nums){
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> subset :  result) {
                List<Integer> newSubset = new ArrayList<Integer>(subset);
                newSubset.add(num);
                newSubsets.add(newSubset);
            }
            result.addAll(newSubsets);
        }
        return result;
    }

    /**
     * 方法2  nums = [1,2,3]
     */
    List<Integer> t1 = new ArrayList<Integer>();
    List<List<Integer>> ans1 = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets2(int[] nums) {
        dfs(0, nums);
        return ans1;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans1.add(new ArrayList<Integer>(t1));
            return;
        }
        t1.add(nums[cur]);
        dfs(cur + 1, nums);
        t1.remove(t1.size() - 1);
        dfs(cur + 1, nums);
    }



}
