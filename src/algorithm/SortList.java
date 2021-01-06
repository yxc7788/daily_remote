package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
 */
public class SortList {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    /**
     * 归并排序：递归方法
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
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

    /**
     * 非递归方法，这个方法是O(1)的空间复杂度
     */
    public ListNode sortList2(ListNode head) {
        int length = listLength(head);
        ListNode res = new ListNode(Integer.MIN_VALUE, head);

        for (int intv = 1; intv < length; intv *= 2) {
            ListNode prev = res;
            ListNode h = res.next;
            while (h != null) {
                // BEGIN get the two merge head `h1`, `h2`
                ListNode h1 = h, h2 = null;
                int i = intv;
                while (i != 0 && h != null) {
                    h = h.next;
                    i -= 1;
                }
                // no need to merge because the `h2` is None.
                if (i != 0) {
                    break;
                }
                h2 = h;
                // END get the two merge head `h1`, `h2`

                // get next h
                i = intv;
                while (i != 0 && h != null) {
                    h = h.next;
                    i -= 1;
                }

                //BEGIN merge the `h1` and `h2`.
                int c1 = intv, c2 = intv - i;   // the `c2`: length of `h2` can be small than the `intv`.
                while (c1 > 0 && c2 > 0) {
                    if (h1.val < h2.val) {
                        prev.next = h1;
                        h1 = h1.next;
                        c1 -= 1;
                    } else {
                        prev.next = h2;
                        h2 = h2.next;
                        c2 -= 1;
                    }
                    prev = prev.next;
                }
                prev.next = c1 > 0 ? h1 : h2;
                // END merge the `h1` and `h2`

                // let prev be the node before h
                while (c1-- > 0 || c2-- > 0) {
                    prev = prev.next;
                }
                prev.next = h;
            }
        }
        return res.next;
    }

    /**
     * 计算链表长度
     */
    private int listLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length += 1;
            head = head.next;
        }
        return length;
    }

}
