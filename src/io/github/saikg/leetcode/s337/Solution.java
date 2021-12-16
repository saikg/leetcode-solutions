package io.github.saikg.leetcode.s337;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<TreeNode, Map<Boolean, Integer>> dp;

    public int rob(TreeNode root) {
        dp = new HashMap<>();
        return rob(root, false);
    }

    private int rob(TreeNode node, boolean prev) {
        if (node == null) {
            return 0;
        }

        if (dp.getOrDefault(node, Collections.emptyMap()).containsKey(prev)) {
            return dp.get(node).get(prev);
        }

        int ans = 0;
        if (prev) {
            ans = rob(node.left, false) + rob(node.right, false);
        } else {
            ans = Math.max(
                    node.val + rob(node.left, true) + rob(node.right, true),
                    rob(node.left, false) + rob(node.right, false)
            );
        }
        dp.computeIfAbsent(node, n -> new HashMap<>()).put(prev, ans);
        return dp.get(node).get(prev);
    }
}