package io.github.saikg.leetcode.s144;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        return iterativePreorderTraversal(root);
    }

    private void preorderTraversal(TreeNode root, List<Integer> traversal) {
        if (root == null) {
            return;
        }

        traversal.add(root.val);
        preorderTraversal(root.left, traversal);
        preorderTraversal(root.right, traversal);
    }

    private List<Integer> iterativePreorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            curr = stack.pop();
            traversal.add(curr.val);
            
            while (curr != null) {

            }
        }

        return traversal;
    }
}