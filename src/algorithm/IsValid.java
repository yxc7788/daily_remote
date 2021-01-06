package algorithm;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static javax.swing.UIManager.put;

/**
 * @author yangxc27652
 * @date 2020/12/22
 * @description 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 输入: "()"
 * 输出: true
 * 输入: "()[]{}"
 * 输出: true
 * 输入: "(]"
 * 输出: false
 * 输入: "([)]"
 * 输出: false
 * 输入: "{[]}"
 * 输出: true
 *https://leetcode-cn.com/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/
 */
public class IsValid {


    /**
     * 栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；
     * 建立哈希表 dic 构建左右括号对应关系：keykey 左括号，valuevalue 右括号；这样查询 22 个括号是否对应只需 O(1)O(1) 时间复杂度；
     * 建立栈 stack，遍历字符串 s 并按照算法流程一一判断。
     * 算法流程
     * 如果 c 是左括号，则入栈 pushpush；
     * 否则通过哈希表判断括号对应关系，若 stack 栈顶出栈括号 stack.pop() 与当前遍历括号 c 不对应，则提前返回 falsefalse。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }

}
