package io.github.saikg.leetcode.common.linkedlist;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode node) {
        this.val = val;
        this.next = node;
    }

    public void traverse() {
        ListNode t = this;
        while (t.next != null) {
            System.out.printf("%d -> ", t.val);
            t = t.next;
        }
        System.out.println();
    }

    public void addLast(ListNode node) {
        ListNode curr = this;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = node;
    }
}
