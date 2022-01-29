package io.github.saikg.leetcode.s503;

import java.util.Stack;

public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            stack.push(i);
        }

        int[] indices = new int[n];
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            indices[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i);
        }
        return indices;
    }
}