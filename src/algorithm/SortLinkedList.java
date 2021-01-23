package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yangxc27652
 * @date 2021/1/14
 * @description  148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 */
public class SortLinkedList {

    /**
     * s. 空间复杂度超了
     */
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode nHead = head;
        ListNode node = dummy;
        while (nHead != null) {
            queue.add(nHead);
            nHead = nHead.next;
        }
        while (!queue.isEmpty()) {
            node.next = new ListNode(queue.poll().val);
            node = node.next;
        }
        return dummy.next;
    }


    /**
     * method2
     * https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
     */
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

}
