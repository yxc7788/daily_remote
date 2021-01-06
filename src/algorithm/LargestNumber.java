package algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yangxc27652
 * @date 2020/12/22
 * @description 179. 最大数
 * 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * https://leetcode-cn.com/problems/largest-number/solution/zui-da-shu-by-leetcode/
 */
public class LargestNumber {


    /**
     * 方法1，仿照牛客，但是此题目要考虑 [0,0]的情况
     */
    public String largestNumber1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int len = nums.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++){
            str[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(str,new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c2.compareTo(c1);
            }
        });
        // 注意这里必须equals不能 ==
        if ("0".equals(str[0])) {
            return "0";
        }
        for (int i = 0; i < len; i++){
            sb.append(str[i]);
        }
        return sb.toString();
    }

    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if ("0".equals(asStrs[0])) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }


}
