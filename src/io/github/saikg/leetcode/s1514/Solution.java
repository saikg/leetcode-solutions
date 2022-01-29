package io.github.saikg.leetcode.s1514;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public double maxProbability(int n, int[][] edges,
                                 double[] successProb,
                                 int start, int end) {
        List<List<double[]>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(new double[]{v, successProb[i]});
            adjList.get(v).add(new double[]{u, successProb[i]});
        }

        double[] probability = new double[n];
        probability[start] = 1;

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> Double.compare(probability[b], probability[a]));
        queue.add(start);

        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()) {
            int f = queue.poll();
            if (visited[f]) {
                continue;
            }
            visited[f] = true;

            for (double[] edge : adjList.get(f)) {
                Integer v = (int)edge[0];
                double w = edge[1];
                if (w * probability[f] >= probability[v]) {
                    probability[v] = w * probability[f];
                    queue.add(v);
                }
            }
        }

        return probability[end];
    }
}