package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 输入: "cbbd"
 * 输出: "bb"
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class LongestPalindrome {

    /**
     * 中心扩散法
     */
    public String longestPalindrome0(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }




    /**
     * 思路
     * 一个回文去掉两头以后，剩下的部分依然是回文（这里暂不讨论边界情况）；
     * 依然从回文串的定义展开讨论：
     *
     * 如果一个字符串的头尾两个字符都不相等，那么这个字符串一定不是回文串；
     * 如果一个字符串的头尾两个字符相等，才有必要继续判断下去。
     * 如果里面的子串是回文，整体就是回文串；
     * 如果里面的子串不是回文串，整体就不是回文串。
     * 即：在头尾字符相等的情况下，里面子串的回文性质据定了整个子串的回文性质，
     * 这就是状态转移。因此可以把「状态」定义为原字符串的一个子串是否为回文子串。
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)，即存储动态规划状态需要的空间。
     */
    public String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 注意maxLen值一定从1开始不能是0
        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                // 注意这个地方不能写成  max = Math.max(max, j - i + 1)这样，因为这里要记录最大max时候的begin
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    /**
     * self
     */
    public String longestPalindromex(String s) {

        char[] chars = s.toCharArray();
        int[][] dp = new int[chars.length][chars.length];

        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }
        int begin = 0;
        int max = 1;
        for (int j = 1; j < chars.length; j++) {
            for (int i = 0 ; i < j; i++) {
                if (chars[i] == chars[j]) {
                    if (j - i < 3) {
                        dp[i][j] = 1;
                    }
                    else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                    if (dp[i][j] == 1 && j - i + 1 > max) {
                        max = j - i + 1;
                        begin = i;
                    }
                }
            }
        }
        return s.substring(begin, begin + max);

    }

    /**
     * method2 暴力
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
