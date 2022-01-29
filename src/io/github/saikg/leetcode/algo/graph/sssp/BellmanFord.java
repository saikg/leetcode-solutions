package io.github.saikg.leetcode.algo.graph.sssp;

import java.util.Arrays;

/*
* Single source shortest path algorithm using dynamic programming
* Space complexity: O(|V|)
* Time complexity: O(|V|.|E|)
* */
public class BellmanFord {

    private static final int INF = Integer.MAX_VALUE;

    public int[] shortestPath(int src, int[][] edges, int nodes) {
        int[] distance = new int[nodes];
        Arrays.fill(distance, INF);

        // update all distances to the shortest path weights
        distance[src] = 0;
        for (int i = 0; i < nodes - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                // relaxation step
                if (distance[u] != INF && distance[v] > distance[u] + w) {
                    distance[v] = distance[u] + w;
                }
            }
        }

        // check for negative weight cycles
        boolean hasNegativeWeightCycle = false;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (distance[u] != INF && distance[v] > distance[u] + w) {
                hasNegativeWeightCycle = true;
                break;
            }
        }

        return hasNegativeWeightCycle ? null : distance;
    }
}
