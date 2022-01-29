package io.github.saikg.leetcode.algo.graph.sssp;

import java.util.*;

/*
* Single source shortest path algorithm using BFS traversal
* Space complexity: O(|V|)
* Time complexity: O(|V| + |E|.log(|V|))
* */
public class Dijkstra {

    private static final int INF = Integer.MAX_VALUE;

    public int[] shortestPaths(int source, int[][] edges, int nodes) {
        boolean[] visited = new boolean[nodes];
        int[] distance = new int[nodes];
        Arrays.fill(distance, INF);
        distance[source] = 0;

        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adjList.get(u).add(new int[]{v, w});
        }

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(u -> distance[u]));
        queue.add(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            int d = distance[u];

            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (int[] edge : adjList.get(u)) {
                int v = edge[0], w = edge[1];
                if (w + d < distance[v]) {
                    distance[v] = w + d;
                    queue.add(v);
                }
            }
        }

        return distance;
    }
}
