package io.github.saikg.leetcode.s83;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
//        ListNode back = head, front = head;
//        while (back != null) {
//            while (front != null && back.val == front.val) {
//                front = front.next;
//            }
//            back.next = front;
//            back = back.next;
//        }
//        return head;
        return singleNodeSolution(head);
    }

    private ListNode singleNodeSolution(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.val == curr.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}