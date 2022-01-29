package io.github.saikg.leetcode.s701;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val > root.val) {
            root.right =  insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}
