package io.github.saikg.leetcode.s1011;

import java.util.Arrays;

public class Solution {

    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int totalWeight = Arrays.stream(weights).sum();

        int minCapacity = 0, maxCapacity = totalWeight;
        while (minCapacity < maxCapacity) {
            int cap = (minCapacity + maxCapacity) / 2;
            int daysToShip = getDaysToShip(weights, cap);
            if (daysToShip <= days) {
                maxCapacity = cap;
            } else {
                minCapacity = cap+1;
            }
        }
        return minCapacity;
    }

    private int getDaysToShip(int[] weights, int capacity) {
        int days = 0, shippedWt = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > capacity) {
                return Integer.MAX_VALUE;
            }
            int newShippedWt = shippedWt + weights[i];
            if (newShippedWt < capacity) {
                shippedWt = newShippedWt;
            } else if (newShippedWt == capacity) {
                days++;
                shippedWt = 0;
            } else {
                days++;
                shippedWt = weights[i];
            }
        }
        if (shippedWt != 0) {
            days++;
        }
        return days;
    }
}
