package io.github.saikg.leetcode.s235;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {

    private TreeNode LCA;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return LCA;
    }

    private int helper(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return 0;
        }

        int v = (node == q || node == p) ? 1 : 0;
        int l = helper(node.left, p, q);
        int r = helper(node.right, p, q);
        if (v + l + r == 2 && LCA == null) {
            LCA = node;
        }
        return l + r + v;
    }
}