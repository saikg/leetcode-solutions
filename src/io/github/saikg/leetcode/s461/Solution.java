package io.github.saikg.leetcode.s461;

public class Solution {
    public int hammingDistance(int x, int y) {
        int t = x ^ y, ans = 0;
        while (t > 0) {
            t = t & (t - 1);
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.hammingDistance(109, 19);
        System.out.println("ans = " + ans);
    }
}
