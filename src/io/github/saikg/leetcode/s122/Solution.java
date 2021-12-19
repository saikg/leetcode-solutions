package io.github.saikg.leetcode.s122;

public class Solution {
    public int maxProfit(int[] prices) {
        int minHold = prices[0], maxProfit = 0;
        for (int price: prices) {
            if (price > minHold) {
                maxProfit += price - minHold;
                minHold = price;
            }
            minHold = Math.min(price, minHold);
        }
        return maxProfit;
    }
}