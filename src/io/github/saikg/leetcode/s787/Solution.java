package io.github.saikg.leetcode.s787;

import java.util.*;

public class Solution {

    private static final int INF = Integer.MAX_VALUE;

    // Bellman-Ford algorithm
    public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, INF);

        cost[src] = 0;
        for (int round = 0; round < k + 1; round++) {
            int[] updatedCost = new int[n];
            System.arraycopy(cost, 0, updatedCost, 0, n);
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                if (cost[from] != INF && cost[to] > cost[from] + price) {
                    updatedCost[to] = cost[from] + price;
                }
            }
            cost = updatedCost;
        }

        return cost[dest] == INF ? -1 : cost[dest];
    }
}