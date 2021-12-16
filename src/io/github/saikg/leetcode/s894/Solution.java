package io.github.saikg.leetcode.s894;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Collections.singletonList(new TreeNode(0));
        }

        List<TreeNode> result = new ArrayList<>();
        for (int i = 1; i < n-1; i+=2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - 1 - i);
            for (TreeNode leftRoot: left) {
                for (TreeNode rightRoot: right) {
                    TreeNode node = new TreeNode(0);
                    node.left = leftRoot;
                    node.right = rightRoot;
                    result.add(node);
                }
            }
        }
        return result;
    }
}