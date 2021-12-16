package io.github.saikg.leetcode.s92;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }

        ListNode preLeft = head;
        while (preLeft.next != null && preLeft.next.val == left) {
            preLeft = preLeft.next;
        }

        if (preLeft.next == null) {
            return head;
        }

        ListNode prev = preLeft;
        ListNode rightNode = preLeft.next;
        while (rightNode != null && rightNode.val != right) {
            ListNode tmp = rightNode.next;
            rightNode.next = prev;
            prev = rightNode;
            rightNode = tmp;
        }
        if (rightNode == null) {
            return head;
        }

        preLeft.next.next = rightNode.next;
        preLeft.next = rightNode;
        traverse(head);
        return head;
    }

    private void traverse(ListNode node) {
        while (node != null) {
            System.out.printf("%d -> ", node.val);
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.addLast(new ListNode(2));
        head.addLast(new ListNode(3));
        head.addLast(new ListNode(4));
        head.addLast(new ListNode(5));

        Solution solution = new Solution();
        solution.traverse(head);
        solution.reverseBetween(head, 2, 4);
        solution.traverse(head);
    }
}