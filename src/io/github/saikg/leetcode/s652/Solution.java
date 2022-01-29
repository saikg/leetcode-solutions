package io.github.saikg.leetcode.s652;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private Map<Integer, Integer> seenSubtrees;
    private List<TreeNode> duplicates;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        seenSubtrees = new HashMap<>();
        duplicates = new ArrayList<>();
        dfs(root);
        return duplicates;
    }

    private String dfs(TreeNode node) {
        if (node == null) {
            return "N";
        }

        StringBuilder encodedTree = new StringBuilder();
        encodedTree.append(node.val).append(".");
        encodedTree.append(dfs(node.left)).append(".");
        encodedTree.append(dfs(node.right)).append(".");

        int hashCode = encodedTree.toString().hashCode();
        seenSubtrees.put(hashCode, seenSubtrees.getOrDefault(hashCode, 0) + 1);

        if (seenSubtrees.get(hashCode) == 2) {
            duplicates.add(node);
        }
        return encodedTree.toString();
    }
}
