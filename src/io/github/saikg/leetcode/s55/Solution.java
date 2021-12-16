package io.github.saikg.leetcode.s55;

public class Solution {
    public boolean canJump(int[] nums) {
        int lastPos = 0, n = nums.length;
        for (int i = 0; i <= Math.min(lastPos, n-1); i++) {
            lastPos = Math.max(i + nums[i], lastPos);
        }
        return lastPos >= n-1;
    }
}