package io.github.saikg.leetcode.s876;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}