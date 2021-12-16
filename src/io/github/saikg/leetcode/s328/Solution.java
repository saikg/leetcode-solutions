package io.github.saikg.leetcode.s328;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head;
        ListNode oddHead = head.next;
        doS(evenHead, oddHead);
        traverse(evenHead);
        traverse(oddHead);
        evenHead.next = head.next;
        return head;
    }

    private void doS(ListNode e, ListNode o) {
        if (o == null) {
            return;
        }
        e.next = o.next;
        e = e.next;
        doS(o, e);
    }

    private void traverse(ListNode n) {
        while (n != null) {
            System.out.printf("%d ", n.val);
            n = n.next;
        }
    }
}
