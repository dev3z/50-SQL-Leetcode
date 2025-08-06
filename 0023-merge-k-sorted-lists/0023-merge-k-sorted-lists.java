/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 import java.util.Arrays;
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int size = lists.length;
        if (lists.length == 1) {
            ListNode h = lists[0];
            return h;
        }
        int mid = size/2;
        ListNode left = mergeKLists(Arrays.copyOfRange(lists,0,mid));
        ListNode right = mergeKLists(Arrays.copyOfRange(lists,mid,size));
        return Merge(left,right);
    }

    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode AnsHead = new ListNode();
        ListNode temp = AnsHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                temp = temp.next;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }

        }

        if (list1 == null) {
            temp.next = list2;
        } else {
            temp.next = list1;
        }

        return AnsHead.next;

    }
}