package io.github.saikg.leetcode.s322;

import java.util.Arrays;

public class Solution {

    private static int MAX_AMOUNT = 10001;

    public int coinChange(int[] coins, int amount) {
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, MAX_AMOUNT);
        minCoins[0] = 0;
        for (int coin: coins) {
            for (int i = 0; i + coin < minCoins.length; i++) {
                minCoins[i + coin] = Math.min(minCoins[i] + 1, minCoins[i + coin]);
            }
        }
        return minCoins[amount] == MAX_AMOUNT ? -1 : minCoins[amount];
    }
}
