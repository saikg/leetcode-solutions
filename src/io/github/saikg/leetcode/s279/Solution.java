package io.github.saikg.leetcode.s279;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    Map<Integer, Integer> cache = new HashMap<>();

    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int ans = Integer.MAX_VALUE;
        for (int p = 1; p*p <= n; p++) {
            ans = Math.min(ans, 1 + numSquares(n - p*p));
        }

        cache.put(n, ans);
        return ans;
    }
}