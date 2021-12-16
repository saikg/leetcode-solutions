package io.github.saikg.leetcode.s477;

public class Solution {
    public int totalHammingDistance(int[] nums) {
        return countBits(nums);
    }

    // O(B * n) implementation
    private int countBits(int[] nums) {
        int n = nums.length, ans = 0;
        for (int s = 0; s < 31; s++) {
            int count = 0, t = 1 << s;
            for (int i = 0; i < n; i++) {
                count += ((t & nums[i]) != 0) ? 1 : 0;
            }
            ans += count * (n - count);
        }
        return ans;
    }

    // O(B * n^2) implementation; TLE
    private int naive(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans += hammingDistance(nums[i], nums[j]);
            }
        }
        return ans;
    }

    private int hammingDistance(int x, int y) {
        int t = x ^ y, ans = 0;
        while (t != 0) {
            t = t & (t - 1);
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 14, 4};

        Solution solution = new Solution();
        int ans = solution.totalHammingDistance(nums);
        System.out.println("ans = " + ans);
    }
}
