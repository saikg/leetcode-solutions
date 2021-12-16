package io.github.saikg.leetcode.s450;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val)
            root.right = deleteNode(root.right, key);
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null)
                root = null;
            else if (root.right != null) {
                root.val = successor(root).val;
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root).val;
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    private TreeNode successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root;
    }

    private TreeNode predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root;
    }
}
