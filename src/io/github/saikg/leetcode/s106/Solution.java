package io.github.saikg.leetcode.s106;

import io.github.saikg.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

// Binary Tree construction from inorder and postorder traversals
public class Solution {

    int[] inorder;
    int[] postorder;

    int postorderIndex;
    Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;

        postorderIndex = postorder.length - 1;
        indexMap = new HashMap<>();
        int idx = 0;
        for (int val: inorder) {
            indexMap.put(val, idx++);
        }

        return recursiveBuild(0, postorderIndex);
    }

    private TreeNode recursiveBuild(int left, int right) {
        if (left > right) {
            return null;
        }

        int rootVal = postorder[postorderIndex];
        TreeNode root = new TreeNode(rootVal);

        postorderIndex--;
        int inorderIndex = indexMap.get(rootVal);
        root.right = recursiveBuild(inorderIndex + 1, right);
        root.left = recursiveBuild(left, inorderIndex - 1);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        Solution solution = new Solution();
        TreeNode root = solution.buildTree(inorder, postorder);
        System.out.println("root = " + root);
    }
}
