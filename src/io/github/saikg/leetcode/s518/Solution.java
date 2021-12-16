package io.github.saikg.leetcode.s518;

public class Solution {
    public int change(int amount, int[] coins) {
        int[] numWays = new int[amount + 1];

        numWays[0] = 1;
        for (int coin: coins) {
            numWays[coin] = 1;
        }

        for (int amt = 0; amt <= amount; amt++) {
            int ways = 0;
            for (int l = 0; l <= amt -l; l++) {
                ways += numWays[l] * numWays[amt - l];
            }
            numWays[amt] = ways;
        }
        return numWays[amount];
    }
}