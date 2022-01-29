package io.github.saikg.leetcode.s1305;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> sorted = new ArrayList<>();

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        TreeNode node1 = root1;
        TreeNode node2 = root2;

        while (node1 != null || node2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {

            enqueueLeftNodes(stack1, node1);
            enqueueLeftNodes(stack2, node2);

            switch (treeWithSmallerValue(stack1, stack2)) {
                case 1:
                    node1 = stack1.pop();
                    sorted.add(node1.val);
                    node1 = node1.right;
                case 2:
                    node2 = stack2.pop();
                    sorted.add(node2.val);
                    node2 = node2.right;
            }
        }

        return sorted;
    }

    private void enqueueLeftNodes(Stack<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.add(node);
            node = node.left;
        }
    }

    private int treeWithSmallerValue(Stack<TreeNode> stack1, Stack<TreeNode> stack2) {
        if (stack2.isEmpty()) {
            return 1;
        } else if (stack1.isEmpty()) {
            return 2;
        } else {
            if (stack1.peek().val < stack2.peek().val) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}