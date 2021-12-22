package io.github.saikg.leetcode.s206;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode reverseList(ListNode head) {
        return reverseIterative(head);
    }

    private ListNode reverseIterative(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode t = curr.next;
            curr.next = prev;
            prev = curr;
            curr = t;
        }
        return prev;
    }

    public ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseRecursive(head.next);
        p.next.next = head;
        head.next = null;
        return head;
    }
}