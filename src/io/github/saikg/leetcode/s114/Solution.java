package io.github.saikg.leetcode.s114;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {

    private TreeNode prev;

    public void flatten(TreeNode treeNode) {
        preOrderTraversal(treeNode);
    }

    private void preOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode left = treeNode.left;
        TreeNode right = treeNode.right;
        treeNode.left = null;
        treeNode.right = null;

        if (prev != null) {
            prev.right = treeNode;
        }
        prev = treeNode;

        preOrderTraversal(left);
        preOrderTraversal(right);
    }
}