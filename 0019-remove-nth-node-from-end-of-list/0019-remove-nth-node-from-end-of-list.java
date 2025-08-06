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
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int ctr = 0;
        ListNode curr = head;

        //Count total number of nodes
        while (curr != null) {
            curr = curr.next;
            ctr++;
        }

        int removal = ctr - n;

        //removing the head
        if (removal == 0) {
            return head.next;
        }

        //Traverse to the node before the one to remove
        curr = head;
        for (int i = 1; i < removal; i++) {
            curr = curr.next;
        }

        // Skip the node to be removed
        if (curr.next != null) {
            curr.next = curr.next.next;
        }

        return head;
    }
}