package io.github.saikg.leetcode.s95;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        if (start > end) {
            return Collections.singletonList(null);
        }

        List<TreeNode> allTrees = new ArrayList<>();
        for (int root = start; root <= end; root++) {
            List<TreeNode> leftSubTrees = generateTrees(start, root - 1);
            List<TreeNode> rightSubTrees = generateTrees(root + 1, end);

            for (TreeNode lst: leftSubTrees) {
                for (TreeNode rst: rightSubTrees) {
                    TreeNode rootNode = new TreeNode(root);
                    rootNode.left = lst;
                    rootNode.right = rst;
                    allTrees.add(rootNode);
                }
            }
        }
        return allTrees;
    }
}