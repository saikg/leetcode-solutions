package io.github.saikg.leetcode.s207;

import java.util.*;

// topological sort based on kahn's algorithm
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        int n = numCourses;
        int[] inorder = new int[n];
        Arrays.fill(inorder, 0);
        for (int[] prerequisite: prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            inorder[to]++;
            adjList.computeIfAbsent(from, v -> new ArrayList<>()).add(to);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inorder[i] == 0) {
                queue.add(i);
            }
        }

        int processedNodes = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int v: adjList.getOrDefault(node, Collections.emptyList())) {
                inorder[v]--;
                if (inorder[v] == 0) {
                    queue.add(v);
                }
            }
            processedNodes++;
        }
        return processedNodes == n;
    }
}
