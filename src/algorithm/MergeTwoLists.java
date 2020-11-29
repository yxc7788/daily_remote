package algorithm;

/**
 * @author yangxc27652
 * @date 2020/11/29
 * @description
 */
public class MergeTwoLists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode mergeTwoLists(ListNode linked1, ListNode linked2) {
        //下面4行是空判断
        if (linked1 == null) {
            return linked2;
        }

        if (linked2 == null) {
            return linked1;
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (linked1 != null && linked2 != null) {
            //比较一下，哪个小就把哪个放到新的链表中
            if (linked1.val <= linked2.val) {
                curr.next = linked1;
                linked1 = linked1.next;
            } else {
                curr.next = linked2;
                linked2 = linked2.next;
            }
            curr = curr.next;
        }
        //然后把那个不为空的链表挂到新的链表中
        curr.next = linked1 == null ? linked2 : linked1;
        return dummy.next;
    }

    /**
     * method 2
     */
    public ListNode mergeTwoLists2(ListNode linked1, ListNode linked2) {
        if (linked1 == null) {
            return linked2;

        }
        if (linked2 == null) {
            return linked1;
        }
        if (linked1.val < linked2.val) {
            linked1.next = mergeTwoLists(linked1.next, linked2);
            return linked1;
        } else {
            linked2.next = mergeTwoLists(linked1, linked2.next);
            return linked2;
        }
    }

}
