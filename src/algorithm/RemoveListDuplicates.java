package algorithm;

/**
 * @author yangxc27652
 * @date 2020/12/10
 * @description 删除链表中的重复元素
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * http://www.mamicode.com/info-detail-2729469.html
 */
public class RemoveListDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        while (p != null){
            // 注意这里 p.next 要判断为null，从空指针角度也能想到
            if (p.next != null && p.val == p.next.val){
                p.next = p.next.next;
            }else{
                p = p.next;
            }
        }
        return head;
    }
}
