package io.github.saikg.leetcode.s1101;

import java.util.Arrays;

public class Solution {

    int[] parent, size;

    public int earliestAcq(int[][] logs, int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));
        for (int[] log: logs) {
            int time = log[0];
            int person1 = log[1];
            int person2 = log[2];
            union(person1, person2);
            if (size[root(person1)] == n) {
                return time;
            }
        }
        return -1;
    }

    private int root(int a) {
        while (parent[a] != a) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }

    private void union(int a, int b) {
        int rootA = root(a);
        int rootB = root(b);
        if (rootA == rootB)
            return;

        if (size[rootA] >= size[rootB]) {
            parent[rootB] = parent[rootA];
            size[rootA] += size[rootB];
        } else {
            parent[rootA] = parent[rootB];
            size[rootB] += size[rootA];
        }
    }
}
