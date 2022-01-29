package io.github.saikg.leetcode.s1026;

import io.github.saikg.leetcode.common.TreeNode;

public class Solution {

    private int ans;

    public int maxAncestorDiff(TreeNode root) {
        ans = Integer.MIN_VALUE;
        maxAncestorDiff(root, null, null);
        return ans;
    }

    private void maxAncestorDiff(TreeNode root, Integer maxAncestor, Integer minAncestor) {
        if (root == null) {
            return;
        }

        int diff = 0;
        if (maxAncestor != null) {
            diff = maxDifference(root.val, minAncestor, maxAncestor);
            maxAncestor = Math.max(maxAncestor, root.val);
            minAncestor = Math.min(minAncestor, root.val);
        } else {
            maxAncestor = root.val;
            minAncestor = root.val;
        }
        ans = Math.max(ans, diff);

        maxAncestorDiff(root.left, maxAncestor, minAncestor);
        maxAncestorDiff(root.right, maxAncestor, minAncestor);
    }

    private int maxDifference(int a, int low, int high) {
        return Math.max(
                Math.abs(a - low),
                Math.abs(a - high)
        );
    }
}