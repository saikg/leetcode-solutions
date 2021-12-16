package io.github.saikg.leetcode.s45;

import java.util.Arrays;

public class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int currJump = 0, nextJump = 0, jumps = 0;
        for (int i = 0; i < n; i++) {
            nextJump = Math.max(i + nums[i], nextJump);
            if (i == currJump) {
                jumps++;
                currJump = nextJump;
            }
        }
        return jumps;
    }
}