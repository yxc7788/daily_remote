package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxc27652
 * @date 2020/12/23
 * @description  93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"] 
 */
public class RestoreIpAddresses {


    /**
     * 由于我们需要找出所有可能复原出的 IP 地址，因此可以考虑使用递归的方法，对所有可能的字符串分隔方式进行搜索，并筛选出满足要求的作为答案。
     * 设题目中给出的字符串为 ss。我们用递归函数 \textit{dfs}(\textit{segId}, \textit{segStart})dfs(segId,segStart)
     * 表示我们正在从 s[\textit{segStart}]s[segStart] 的位置开始，搜索 IP 地址中的第 \textit{segId}segId 段，
     * \textit{segId} \in \{0, 1, 2, 3\}segId∈{0,1,2,3}。由于 IP 地址的每一段必须是 [0, 255][0,255] 中的整数，
     * 因此我们从 \textit{segStart}segStart 开始，从小到大依次枚举当前这一段 IP 地址的结束位置 \textit{segEnd}segEnd。
     * 如果满足要求，就递归地进行下一段搜索，调用递归函数 \textit{dfs}(\textit{segId} + 1, \textit{segEnd} + 1)dfs(segId+1,segEnd+1)。
     * 特别地，由于 IP 地址的每一段不能有前导零，因此如果 s[\textit{segStart}]s[segStart] 等于字符 00，
     * 那么 IP 地址的第 \textit{segId}segId 段只能为 00，需要作为特殊情况进行考虑。
     * 在递归搜索的过程中，如果我们已经得到了全部的 44 段 IP 地址（即 \textit{segId} = 4segId=4），
     * 并且遍历完了整个字符串（即 \textit{segStart} = |s|segStart=∣s∣，其中 |s|∣s∣ 表示字符串 ss 的长度），
     * 那么就复原出了一种满足题目要求的 IP 地址，我们将其加入答案。在其它的时刻，如果提前遍历完了整个字符串，那么我们需要结束搜索，回溯到上一步。
     */
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    //   输入：s = "101023"
    // 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"] 

    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }


    /**
     * 暴力
     */
    public List<String> restoreIpAddresses2(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder ip = new StringBuilder();

        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        /*
                         * 1、保障下面subString不会越界
                         * 2、保障截取的字符串与输入字符串长度相同
                         * //1、2比较好理解，3比较有意思
                         * 3、不能保障截取的字符串转成int后与输入字符串长度相同
                         * 如：字符串010010，a=1，b=1，c=1，d=3，对应字符串0，1，0，010
                         * 转成int后seg1=0，seg2=1，seg3=0，seg4=10
                         * //所以需要下面这处判断if (ip.length() == s.length() + 3)
                         */
                        if (a + b + c + d == s.length()) {
                            int seg1 = Integer.parseInt(s.substring(0, a));
                            int seg2 = Integer.parseInt(s.substring(a, a + b));
                            int seg3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int seg4 = Integer.parseInt(s.substring(a + b + c, a + b + c + d));
                            // 四个段数值满足0~255
                            if (seg1 <= 255 && seg2 <= 255 && seg3 <= 255 && seg4 <= 255) {
                                ip.append(seg1).append(".").append(seg2).append(".").
                                        append(seg3).append(".").append(seg4);
                                // 保障截取的字符串转成int后与输入字符串长度相同
                                if (ip.length() == s.length() + 3) {
                                    result.add(ip.toString());
                                }
                                ip.delete(0, ip.length());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
