package io.github.saikg.leetcode.s563;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {

    int ans;

    public int findTilt(TreeNode root) {
        ans = 0;
        updateTilts(root);
        return ans;
    }

    private int updateTilts(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = updateTilts(node.left);
        int right = updateTilts(node.right);
        ans += Math.abs(left - right);
        return node.val + left + right;
    }
}