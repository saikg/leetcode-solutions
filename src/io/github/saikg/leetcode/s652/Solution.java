package io.github.saikg.leetcode.s652;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    Map<TreeNode, TreeNode> childToParentMap;

    List<TreeNode> leaves;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        childToParentMap = new HashMap<>();
        leaves = new ArrayList<>();
        populateParentsMap(root, null);

        List<TreeNode> duplicates = new ArrayList<>();

        Map<Integer, List<TreeNode>> candidates = new HashMap<>();
        for (TreeNode node: leaves) {
            candidates.computeIfAbsent(node.val, v -> new ArrayList<>()).add(node);
        }
        candidates = candidates.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(v -> v.getKey(), v -> v.getValue()));

        for (int key: candidates.keySet()) {
            List<TreeNode> nodes = candidates.get(key);
            int numNodes = nodes.size();

            for (int i = 0; i < numNodes; i++) {
                for (int j = i + 1; j < numNodes; j++) {
                    if (matchTrees(nodes.get(i), nodes.get(j))) {

                    }
                }
            }
        }

        return duplicates;
    }

    void populateParentsMap(TreeNode node, TreeNode parent) {
        if (node == null) return;

        childToParentMap.put(node, parent);
        if (node.left == null && node.right == null) {
            leaves.add(node);
            return;
        }

        populateParentsMap(node.left, node);
        populateParentsMap(node.right, node);
    }

    boolean matchTrees(TreeNode rootA, TreeNode rootB) {
        if (rootA == null ^ rootB == null) {
            return false;
        }
        if (rootA == null) {
            return true;
        }
        return rootA.val == rootB.val &&
                matchTrees(rootA.left, rootB.left) &&
                matchTrees(rootA.right, rootB.right);
    }

}