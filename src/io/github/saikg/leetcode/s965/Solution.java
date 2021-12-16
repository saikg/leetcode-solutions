package io.github.saikg.leetcode.s965;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.function.Function;

public class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (fn(root, t -> t.left)) {
            return false;
        }
        if (fn(root, t -> t.right)) {
            return false;
        }
        return isUnivalTree(root.left) && isUnivalTree(root.right);
    }

    private boolean fn(TreeNode node, Function<TreeNode, TreeNode> keyExtractor) {
        TreeNode child = keyExtractor.apply(node);
        return !(child != null && child.val != node.val);
    }
}
