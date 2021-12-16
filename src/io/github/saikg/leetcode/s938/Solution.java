package io.github.saikg.leetcode.s938;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {

    int ans;

    public int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        DFS(root, low, high);
        return ans;
    }

    private void DFS(TreeNode node, int low, int high) {
        if (node == null) {
            return;
        }

        if (low <= node.val && node.val <= high) {
            ans += node.val;
        }
        if (low < node.val) {
            DFS(node.left, low, high);
        }
        if (node.val < high) {
            DFS(node.right, low, high);
        }
    }
}