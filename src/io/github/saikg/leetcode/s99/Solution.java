package io.github.saikg.leetcode.s99;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {
    public void recoverTree(TreeNode node) {
        TreeNode predecessor = null, pred = null, x = null, y =null;
        while (node != null) {
            if (node.left != null) {
                predecessor = node.left;
                while (predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = node;
                    node = node.left;
                } else {
                    if (pred != null && pred.val > node.val) {
                        y = node;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = node;

                    predecessor.right = null;
                    node = node.right;
                }
            } else {
                if (pred != null && pred.val > node.val) {
                    y = node;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = node;
                node = node.right;
            }

            swap(x, y);
        }
    }

    void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}