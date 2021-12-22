package io.github.saikg.leetcode.s143;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

import java.util.Stack;

public class Solution {
    public void reorderList(ListNode head) {
        // reach midpoint
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse nodes past midpoint
        ListNode curr = slow;
        while (slow != null) {
            ListNode t = slow.next;
            slow.next = prev;
            prev = slow;
            slow = t;
        }

//        curr = head;
        traverseList(head);
        traverseList(prev);
//        while (curr != prev) {
//            ListNode t = curr.next;
//            curr.next = prev;
//            curr = curr.next.next;
//            prev = prev.next;
//        }
    }

    private void traverseList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }
}