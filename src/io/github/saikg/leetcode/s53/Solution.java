package io.github.saikg.leetcode.s53;

import java.util.Arrays;

public class Solution {
    //kadane's algorithm
    public int maxSubArray(int[] nums) {
        int curr = 0;
        int ans = Integer.MIN_VALUE;
        for (int num: nums) {
            curr = Math.max(curr + num, num);
            ans = Math.max(ans, curr);
        }
        return ans;
    }
}