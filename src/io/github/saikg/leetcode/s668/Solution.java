package io.github.saikg.leetcode.s668;

import java.util.Arrays;

public class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 1, high = m*n;
        while (low < high) {
            int mid = (low + high) / 2;
            if (!enoughNumbersBelow(mid, m, n, k)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private boolean enoughNumbersBelow(int t, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(t/i, n);
        }
        return count >= k;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findKthNumber(4, 4, 6));
    }
}
