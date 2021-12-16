package io.github.saikg.leetcode.s278;

public class Solution {

    // lower bound implementation of binary search
    public int firstBadVersion(int n) {
        int low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    private boolean isBadVersion(int version) {
        int secret = 77;
        return version >= secret;
    }

    public static void main(String[] args) {
        int n = 100;
        Solution solution = new Solution();
        int ans = solution.firstBadVersion(n);
        System.out.println("ans = " + ans);
    }
}
