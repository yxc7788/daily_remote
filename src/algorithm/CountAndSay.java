package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/26
 * @description 38. 外观数列
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * 前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 *
 */
public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder s = new StringBuilder();
        int p1 = 0;
        int cur = 1;
        if (n == 1) {
            return "1";
        }
        String str = countAndSay(n - 1);
        for (cur = 1; cur < str.length(); cur++) {
            if (str.charAt(p1) != str.charAt(cur)) {
                // 如果碰到当前字符与前面紧邻的字符不等则更新此次结果
                int count = cur - p1;
                s.append(count).append(str.charAt(p1));
                p1 = cur;
            }
        }
        if (p1 != cur){
            // 防止最后一段数相等，如果不等说明p1到cur-1这段字符串是相等的
            int count = cur - p1;
            s.append(count).append(str.charAt(p1));
        }
        return s.toString();
    }


    /**
     * self
     */
    public String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }
        String[] dp = new String[n];
        dp[0] = "1";
        dp[1] = "11";
        for (int i = 2; i < n ; i++) {
            int pre = 0;
            int last = 0;
            StringBuilder sb = new StringBuilder();
            char[] chars = dp[i-1].toCharArray();

            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j] == chars[j+1]) {
                    last = last + 1;
                }
                else {
                    sb.append(last - pre + 1).append(chars[j]);
                    last = j + 1;
                    pre = j + 1;
                }
                // 最后一段做个判读  例如1122333中333的处理，否则没有下面的话，
                if (last == chars.length - 1) {
                    sb.append(last - pre + 1).append(chars[j+1]);
                }
            }
            dp[i] = sb.toString();
        }
        return dp[n - 1];

    }

}
