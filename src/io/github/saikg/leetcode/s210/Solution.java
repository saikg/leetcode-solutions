package io.github.saikg.leetcode.s210;

import java.util.*;

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        int[] inorder = new int[n];
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        Arrays.fill(inorder, 0);
        for (int[] condition: prerequisites) {
            int subject = condition[0];
            int prerequisite = condition[1];

            inorder[subject]++;
            adjacencyList
                    .computeIfAbsent(prerequisite, v -> new ArrayList<>())
                    .add(subject);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inorder[i] == 0) {
                queue.add(i);
            }
        }

        int[] topologicalOrder = new int[n];
        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topologicalOrder[idx] = node;
            idx++;

            for (int subject: adjacencyList.getOrDefault(node, Collections.emptyList())) {
                inorder[subject]--;
                if (inorder[subject] == 0) {
                    queue.add(subject);
                }
            }
        }
        return idx != n ? new int[]{} : topologicalOrder;
    }
}