package io.github.saikg.leetcode.s62;

import java.util.Arrays;

public class Solution {

    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        int[] curr = new int[n];
        Arrays.fill(prev, 1);
        Arrays.fill(curr, 1);

        for (int i = 1; i < m; i++) {
            curr[0] = 1;
            for (int j = 1; j < n; j++) {
                curr[j] = curr[j-1] + prev[j];
            }
            prev = curr;
        }
        return curr[n-1];
    }

}
