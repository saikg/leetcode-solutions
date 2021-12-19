package io.github.saikg.leetcode.s123;

public class Solution {
    public int maxProfit(int[] prices) {
        int days = prices.length;

        int[] forward = new int[days];
        int[] backward = new int[days];

        int minSeen = prices[0];
        for (int day = 1; day < days; day++) {
            forward[day] = Math.max(forward[day-1], prices[day] - minSeen);
            minSeen = Math.min(prices[day], minSeen);
        }

        int maxSeen = prices[days - 1];
        for (int day = days - 2; day >= 0; day--) {
            backward[day] = Math.max(backward[day + 1], maxSeen - prices[day]);
            maxSeen = Math.max(prices[day], maxSeen);
        }

        int ans = 0;
        for (int day = 0; day < days; day++) {
            ans = Math.max(forward[day] + backward[day], ans);
        }

        return ans;
    }

    private int maxProfit(int[] prices, int day, int lastBought, int remainingTransactions) {
        if (remainingTransactions == 0 || day == prices.length) {
            return 0;
        }

        int minHolding = prices[lastBought];
        if (prices[day] < minHolding) {
            return maxProfit(prices, day + 1, day, remainingTransactions);
        }

        int sell = prices[day] - minHolding + maxProfit(prices, day + 1, day, remainingTransactions - 1);
        int hold = maxProfit(prices, day + 1, lastBought, remainingTransactions);
        return Math.max(sell, hold);
    }

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        Solution solution = new Solution();
        int ans = solution.maxProfit(prices);
        System.out.println("ans = " + ans);
    }
}