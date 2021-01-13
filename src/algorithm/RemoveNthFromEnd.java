package algorithm;

/**
 * @author yangxc27652
 * @date 2021/1/10
 * @description 19. 删除链表的倒数第N个节点
 */
public class RemoveNthFromEnd {
    /**
     * self
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (n > 0) {
            fast = fast.next;
            n -- ;
        }
        // 对于特殊情况的处理，比如 输入 [1,2,3]  3, 这种情况是删除头节点，
        if (fast == null) {
            dummy.next = head.next;
            return dummy.next;
        }
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next == null ? null : slow.next.next;
        return head;
    }
}
