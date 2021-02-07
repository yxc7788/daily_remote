package algorithm;

/**
 * @author yangxc27652
 * @date 2021/1/13
 * @description  14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    /**
     * self
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            res = findCommonPre(res, strs[i]);
        }
        return res;
    }
    private String findCommonPre(String a, String b) {
        int min = Math.min(a.length(), b.length());
        int index  = 0;
        while (index < min && a.charAt(index ) == b.charAt(index)) {
            index ++;
        }
        return a.substring(0, index );
    }
}
