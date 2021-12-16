package io.github.saikg.leetcode.s2074;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int group = 1;
        ListNode back = head, front = head;

        while (back != null) {
            int size = 0;
            for (int i = 0; i < group && front != null; i++) {
                front = front.next;
                size++;
            }

            System.out.println(back.val);
            System.out.println(front == null ? null : front.val);
            if (size % 2 == 0) {
                ListNode t = reverseLinkedList(back, front);
                traverse(t);
            }

            back = front;
            group++;
        }
        traverse(head);
        return head;
    }

    private ListNode reverseLinkedList(ListNode node, ListNode end) {
        ListNode curr = node, prev = null;
        while (curr != end) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    void traverse(ListNode t) {
        while (t.next != null) {
            System.out.printf("%d -> ", t.val);
            t = t.next;
        }
        System.out.println();
    }
}