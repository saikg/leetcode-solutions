package io.github.saikg.leetcode.s105;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

// build binary tree from preorder and inorder traversals
public class Solution {

    int[] preorder;
    int[] inorder;

    int preorderIndex;
    Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;

        this.preorder = preorder;
        this.inorder = inorder;

        preorderIndex = 0;
        int idx = 0;
        indexMap = new HashMap<>();
        for (int val: inorder) {
            indexMap.put(val, idx++);
        }
        return buildRecursively(0, n-1);
    }

    private TreeNode buildRecursively(int left, int right) {
        if (left > right) {
            return null;
        }

        int val = preorder[preorderIndex];
        TreeNode root = new TreeNode(val);

        preorderIndex++;

        int idx = indexMap.get(val);
        root.left = buildRecursively(left, idx - 1);
        root.right = buildRecursively(idx + 1, right);
        return root;
    }
}
