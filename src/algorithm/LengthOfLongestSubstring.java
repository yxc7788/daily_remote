package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "abcabcbb"
 * 输出: 3  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 输入: s = "bbbbb"
 * 输出: 1
 * 输入: s = "pwwkew"
 * 输出: 3，因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-jie-suan-fa-3-wu-zhong-fu-zi-fu-de-zui-chang-z/
 */



public class LengthOfLongestSubstring {

    /**
     * 解题思路：滑动窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;//最长子串长度
        int left = 0;//滑动窗口左下标，i相当于滑动窗口右下标
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {//charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。
                left = Math.max(left, map.get(s.charAt(i)) + 1);       //map.get():返回字符所对应的索引，当发现重复元素时，窗口左指针右移
            }        //map.get('a')=0,因为map中只有第一个a的下标，然后更新left指针到原来left的的下一位
            map.put(s.charAt(i), i);      //再更新map中a映射的下标
            max = Math.max(max, i - left + 1);     //比较两个参数的大小
        }
        return max;
    }
}
