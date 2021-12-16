package io.github.saikg.leetcode.s110;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean isBalanced(TreeNode root) {
        return helper(root)[0] == 1;
    }

    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};
        }
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int heightDiff = Math.abs(left[1] - right[1]);
        return new int[]{
                (left[0] * right[0] == 1) && (heightDiff <= 1) ? 1 : 0,
                1 + Math.max(left[1], right[1])
        };
    }
}