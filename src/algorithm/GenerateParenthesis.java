package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/12/23
 * @description  22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class GenerateParenthesis {

    /**
     * 当前左右括号都有大于 00 个可以使用的时候，才产生分支；
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     * 在左边和右边剩余的括号数都等于 00 的时候结算。
     */


    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 简单写法
     */
    private List<String> res = new ArrayList<>();

    public List<String> generateParenthesis1(int n) {
        dfs("", n, 0, 0);
        return res;
    }

    public void dfs(String ans, int n, int lc, int rc) {
        // 剪枝 右边扣号数量大于左边
        if(rc > lc || lc > n) return;
        // 满足结果
        if(lc == n && lc == rc) res.add(ans);
        // 遍历+递归
        dfs(ans+'(', n, lc+1, rc);
        dfs(ans+')', n, lc, rc+1);
    }


    /**
     * self 哪里不对
     */
    public List<String> generateParenthesis2(int n) {
        int left = n;
        int right = n;
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();

        helper(3, 3, res, sb);
        return res;
    }
    private void helper(int left, int right, List<String> res, StringBuilder sb) {
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(sb.toString());

        }
        if (left > 0) {
            sb.append("(");
            helper(left - 1, right, res,sb);
        }
        if (right > 0) {
            sb.append(")");
            helper(left, right - 1, res,sb);
        }
        sb.deleteCharAt(sb.length() - 1);

    }

}
