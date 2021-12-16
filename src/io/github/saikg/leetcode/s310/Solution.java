package io.github.saikg.leetcode.s310;

import java.util.*;

public class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if (n == 1) {
            return Collections.singletonList(0);
        }

        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        int[] inorder = new int[n];
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            inorder[u]++;
            inorder[v]++;

            adjacencyList.computeIfAbsent(u, _u -> new ArrayList<>()).add(v);
            adjacencyList.computeIfAbsent(v, _v -> new ArrayList<>()).add(u);
        }

        List<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inorder[i] == 1) {
                leaves.add(i);
            }
        }

        int processedNodes = 0;
        while (processedNodes < n - 2) {
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf: leaves) {
                for (int v: adjacencyList.getOrDefault(leaf, Collections.emptyList())) {
                    inorder[v]--;
                    if (inorder[v] == 1) {
                        newLeaves.add(v);
                    }
                }
            }
            processedNodes += leaves.size();
            leaves = newLeaves;
        }

        return leaves;
    }
}