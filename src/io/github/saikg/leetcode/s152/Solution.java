package io.github.saikg.leetcode.s152;

public class Solution {
    public int maxProduct(int[] nums) {
        int currMax = 1, ans = nums[0], currMin = 1;
        for (int num: nums) {
            int a = Math.max(num, Math.max(currMax * num, currMin * num));
            int b = Math.min(num, Math.min(currMax * num, currMin * num));
            currMax = a;
            currMin = b;
            ans = Math.max(ans, currMax);
        }
        return ans;
    }
}