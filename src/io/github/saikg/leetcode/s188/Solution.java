package io.github.saikg.leetcode.s188;

import java.util.Arrays;

public class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        }

        int n = prices.length;
        int[] costs = new int[k];
        int[] profits = new int[k];

        Arrays.fill(costs, Integer.MAX_VALUE);

        for (int price: prices) {
            int lastProfit = 0;
            for (int i = 0; i < k; i++) {
                costs[i] = Math.min(costs[i], price - lastProfit);
                profits[i] = Math.max(profits[i], price - costs[i]);
                lastProfit = profits[i];
            }
        }

        return profits[k-1];
    }
}