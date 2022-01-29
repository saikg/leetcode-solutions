package io.github.saikg.leetcode.s1475;

import java.util.Stack;

public class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] discountedPrices = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            int discount = stack.isEmpty() ? 0 : stack.peek();
            discountedPrices[i] = prices[i] - discount;
            stack.push(prices[i]);
        }
        return discountedPrices;
    }
}