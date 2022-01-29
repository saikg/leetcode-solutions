package io.github.saikg.leetcode.s987;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.*;

public class Solution {

    Map<Integer, Map<Integer, List<Integer>>> mappedNodes;
    Set<Integer> offsets;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        mappedNodes = new HashMap<>();
        traverse(root, 0, 0);

        Map<Integer, List<Integer>> nodesByOffset = new HashMap<>();
        for (int depth : mappedNodes.keySet()) {
            Map<Integer, List<Integer>> layer = mappedNodes.get(depth);
            for (int offset : layer.keySet()) {
                layer.get(offset).sort(Integer::compare);
                nodesByOffset
                        .computeIfAbsent(offset, v -> new ArrayList<>())
                        .addAll(layer.get(offset));
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        nodesByOffset.keySet().stream().sorted()
                .forEach(offset -> result.add(nodesByOffset.get(offset)));
        return result;
    }

    private void traverse(TreeNode node, int depth, int offset) {
        if (node == null) {
            return;
        }

        offsets.add(offset);
        mappedNodes
                .computeIfAbsent(depth, v -> new HashMap<>())
                .computeIfAbsent(offset, v -> new ArrayList<>())
                .add(node.val);

        traverse(node.left, depth + 1, offset - 1);
        traverse(node.right, depth + 1, offset + 1);
    }
}