package io.github.saikg.leetcode.s24;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode preHead = new ListNode();
        preHead.next = head;
        ListNode curr = head, prev = preHead;
        while (curr != null) {
            ListNode t = curr.next;
            if (t == null) {
                continue;
            }
            curr.next = t.next;
            t.next = curr;
            prev.next = t;
            prev = curr;
            curr = curr.next;
        }
        return preHead.next;
    }
}