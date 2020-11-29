package algorithm;

import java.util.Stack;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description  请判断一个链表是否为回文链表。输入: 1->2->2->1  输出: true
 * https://leetcode-cn.com/problems/palindrome-linked-list/solution/di-gui-zhan-deng-3chong-jie-jue-fang-shi-zui-hao-d/
 */
public class IsPalindromeLinkedList {

    /**
     *
     * 通过找到链表的中间节点然后把链表后半部分反转（关于链表的反转可以看下432，剑指 Offer-反转链表的3种方式），
     * 最后再用后半部分反转的链表和前半部分一个个比较即可。
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }
        //反转后半部分链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != slow.val) {
                return false;
            }

            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }


    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * method2 我们知道栈是先进后出的一种数据结构，这里还可以使用栈先把链表的节点全部存放到栈中，
     * 然后再一个个出栈，这样就相当于链表从后往前访问了，通过这种方式也能解决，看下代码
     * 我们只需要拿链表的后半部分和前半部分比较即可
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //链表的长度
        int len = 0;
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
            len++;
        }
        //len长度除以2
        len >>= 1;
        //然后再出栈
        while (len-- >= 0) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

}
