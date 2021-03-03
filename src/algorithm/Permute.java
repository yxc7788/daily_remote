package algorithm;

import java.util.*;

/**
 * @author yangxc27652
 * @date 2020/12/3
 * @description  46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * 自己用[1,2,3,4]的例子自己画一下
 * https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        Stack<Integer> path = new Stack<Integer>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     Stack<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.push(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.pop();

            }
        }

    }

}
