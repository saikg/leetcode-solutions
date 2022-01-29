package io.github.saikg.leetcode.s113;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private List<List<Integer>> paths;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        paths = new ArrayList<>();
        pathSum(root, 0, targetSum, new ArrayList<>());
        return paths;
    }

    private void pathSum(TreeNode node, int pathSum, int target, List<Integer> path) {
        if (pathSum > target || node == null) {
            return;
        }

        List<Integer> updatedPath = new ArrayList<>(path);
        updatedPath.add(node.val);
        int updatedPathSum = pathSum + node.val;
        if (isLeafNode(node)) {
            if (updatedPathSum == target) {
                paths.add(updatedPath);
            }
        } else {
            pathSum(node.left, updatedPathSum, target, updatedPath);
            pathSum(node.right, updatedPathSum, target, updatedPath);
        }
    }

    private boolean isLeafNode(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}