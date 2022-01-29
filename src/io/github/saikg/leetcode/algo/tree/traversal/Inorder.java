package io.github.saikg.leetcode.algo.tree.traversal;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Inorder {

    private static final int IMPLEMENTATION = 2;

    public List<TreeNode> get(TreeNode root) {
        return get(root, IMPLEMENTATION);
    }

    private List<TreeNode> get(TreeNode root, int impl) {
        List<TreeNode> traversal = new ArrayList<>();
        switch (impl) {
            case 0:
                recursive(root, traversal);
                return traversal;
            case 1:
                iterative(root, traversal);
                return traversal;
            case 2:
                morris(root, traversal);
                return traversal;
            default:
                return traversal;
        }
    }

    private void recursive(TreeNode node, List<TreeNode> traversal) {
        if (node == null) {
            return;
        }
        recursive(node.left, traversal);
        traversal.add(node);
        recursive(node.right, traversal);
    }

    private void iterative(TreeNode node, List<TreeNode> traversal) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            // go to extreme left
            while (node != null) {
                stack.add(node);
                node = node.left;
            }

            // process left most node
            node = stack.removeLast();
            traversal.add(node);

            // move to right of processed node
            node = node.right;
        }
    }

    private void morris(TreeNode node, List<TreeNode> traversal) {
        TreeNode predecessor = null;
        while (node != null) {
            if (node.left != null) {
                predecessor = node.left;
                while (predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = node;
                    node = node.left;
                } else {
                    traversal.add(node);
                    predecessor.right = null;
                    node = node.right;
                }
            } else {
                traversal.add(node);
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        right.left = new TreeNode(3);
        root.left = left;
        root.right = right;

        Inorder inorder = new Inorder();
        System.out.println(inorder.get(root));
    }
}
