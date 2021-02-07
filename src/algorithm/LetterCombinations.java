package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangxc27652
 * @date 2020/12/26
 * @description 17. 电话号码的字母组合
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode-solutio/
 */
public class LetterCombinations {

    /**
     * self
     * @param digits
     * @return
     */
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    HashMap<Character, String> map = new HashMap<>();
    public List<String> letterCombinations2(String digits) {
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        char[] ds = digits.toCharArray();
        if (ds.length == 0) {
            return res;
        }
        helper(0, ds);
        return res;
    }
    private void helper(int index, char[] ds) {
        if (index == ds.length) {
            res.add(sb.toString());
            return;
        }
        char[] chars = map.get(ds[index]).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            helper(index + 1, ds);
            sb.deleteCharAt(index);
        }
        return;
    }


    /**
     * 这种写法参数比较多
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits.length() == 0) {
            return res;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, phoneMap, digits, 0, new StringBuffer());
        return res;
    }

    public void backtrack(List<String> res, Map<Character, String> phoneMap,
                          String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            res.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                // 比如输入“23” 那么2结束开始3， index+1
                backtrack(res, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

}
