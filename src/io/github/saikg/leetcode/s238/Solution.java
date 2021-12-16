package io.github.saikg.leetcode.s238;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] right = new int[n+1];
        right[n] = 1;
        for (int i = n-1; i >= 0; i--) {
            right[i] = right[i+1] * nums[i];
        }

        int[] products = new int[n];
        int prev = 1;
        for (int i = 0; i < n; i++) {
            products[i] = prev * right[i+1];
            prev = products[i];
        }
        return products;
    }
}