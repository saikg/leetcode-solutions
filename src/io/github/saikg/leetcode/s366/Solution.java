package io.github.saikg.leetcode.s366;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<Integer, List<Integer>> levelToNode = new HashMap<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        markNodes(root);
        return new ArrayList<>(levelToNode.values());
    }

    private int markNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = markNodes(root.left);
        int rightDepth = markNodes(root.right);
        int depth = Math.max(leftDepth, rightDepth);
        levelToNode
                .computeIfAbsent(depth, (v_) -> new ArrayList<>())
                .add(root.val);
        return depth + 1;
    }
}