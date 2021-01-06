package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/26
 * @description 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1

 */
public class StrStr {

    public int strStr(String haystack, String needle) {
        int l = needle.length(), n = haystack.length();

        for (int start = 0; start < n - l + 1; ++start) {
            if (haystack.substring(start, start + l).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * 方法2 双指针法
     */
    public int strStr2(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L == 0) {
            return 0;
        }

        int pn = 0;
        while (pn < n - L + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)){
                ++pn;
            }

            // compute the max match string
            int currLen = 0, pL = 0;
            while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == L) {
                return pn - L;
            }

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }


}
