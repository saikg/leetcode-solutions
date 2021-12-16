package io.github.saikg.leetcode.s141;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (slow != null && fast != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return slow == fast && slow != null;
    }
}