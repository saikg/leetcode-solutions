package io.github.saikg.leetcode.s94;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.*;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        return iterativeInorderTraversal(root);
    }

    private List<Integer> iterativeInorderTraversal(TreeNode root) {
        List<Integer> traversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;


        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            traversal.add(curr.val);
            curr = curr.right;
        }
        return traversal;
    }
}