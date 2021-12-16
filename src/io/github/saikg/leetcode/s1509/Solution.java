package io.github.saikg.leetcode.s1509;

import java.util.Arrays;

public class Solution {

    private static final int ALLOWED_MOVES = 3;

    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= ALLOWED_MOVES + 1) {
            return 0;
        }

        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE, head = 0, tail = n-1-ALLOWED_MOVES;
        for (int i = 0; i <= ALLOWED_MOVES; i++) {
            ans = Math.min(nums[tail+i] - nums[head+i], ans);
        }
        return ans;
    }
}