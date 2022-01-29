package io.github.saikg.leetcode.s1135;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    class UnionFind {

        int n;
        int[] parent;
        int[] size;

        UnionFind(int n) {
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];

            init();
        }

        void init() {
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
            Arrays.fill(this.size, 1);
        }

        void connect(
                int u,
                int v
        ) {
            int rootU = root(u);
            int rootV = root(v);

            int sizeU = this.size[rootU];
            int sizeV = this.size[rootV];

            if (sizeU > sizeV) {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            } else {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            }
        }

        int root(int u) {
            while (parent[u] != u) {
                parent[u] = parent[parent[u]];
                u = parent[u];
            }
            return u;
        }

        boolean connected(int u, int v) {
            return root(u) == root(v);
        }
    }

    /*
    * Kruskal's Algorithm Implementation
    * */
    public int minimumCost(
            int n,
            int[][] connections
    ) {
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));

        int treeSum = 0;
        UnionFind unionFind = new UnionFind(n+1);
        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (!unionFind.connected(u, v)) {
                treeSum += w;
                unionFind.connect(u, v);
            }
        }

        int root = unionFind.root(1);
        return unionFind.size[root] == n ? treeSum : -1;
    }
}