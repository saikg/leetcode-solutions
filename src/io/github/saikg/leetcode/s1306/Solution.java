package io.github.saikg.leetcode.s1306;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> traversal = new LinkedList<>();
        traversal.add(start);
        while (!traversal.isEmpty()) {
            int idx = traversal.poll();
            if (arr[idx] == 0) {
                return true;
            }
            visited[idx] = true;
            int u = idx - arr[idx];
            int v = idx + arr[idx];
            if (u >= 0 && !visited[u]) {
                traversal.add(u);
            }
            if (v < n && !visited[v]) {
                traversal.add(v);
            }
        }
        return false;
    }
}