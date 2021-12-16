package io.github.saikg.leetcode.s959;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    class UnionFind {
        int[] parent, size;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        boolean connected(int a, int b) {
            return root(a) == root(b);
        }

        int root(int a) {
            while (parent[a] != a) {
                a = parent[a];
            }
            return a;
        }

        void union(int a, int b) {
            int rootA = root(a);
            int rootB = root(b);
            if (size[rootA] < size[rootB]) {
                parent[rootA] = parent[rootB];
                size[rootB] += size[rootA];
            } else {
                parent[rootB] = parent[rootA];
                size[rootA] += size[rootB];
            }
        }
    }

    enum Dir {
        TOP(0),
        RIGHT(1),
        BOTTOM(2),
        LEFT(3);

        int v;
        Dir(int e) {
            v = e;
        }
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind unionFind = new UnionFind(4 * n * n);

        int id = 0;
        for (int i = 0; i < n; i++) {
            String row = grid[i];
            for (int j = 0; j < n; j++) {
                int top = index(i, j, Dir.TOP.v, n);
                int right = index(i, j, Dir.RIGHT.v, n);
                int bottom = index(i, j, Dir.BOTTOM.v, n);
                int left = index(i, j, Dir.LEFT.v, n);

                if (row.charAt(j) == '\\') {
                    unionFind.union(top, right);
                    unionFind.union(left, bottom);
                } else if (row.charAt(j) == '/') {
                    unionFind.union(top, left);
                    unionFind.union(right, bottom);
                } else {
                    unionFind.union(top, right);
                    unionFind.union(top, left);
                    unionFind.union(left, bottom);
                }

                if (j > 0) {
                    int leftCellRight = index(i, j-1, Dir.RIGHT.v, n);
                    unionFind.union(left, leftCellRight);
                }
                if (j < n-1) {
                    int rightCellLeft = index(i, j+1, Dir.LEFT.v, n);
                    unionFind.union(right, rightCellLeft);
                }
                if (i > 0) {
                    int topCellBottom = index(i-1, j, Dir.BOTTOM.v, n);
                    unionFind.union(top, topCellBottom);
                }
                if (i < n-1) {
                    int bottomCellTop = index(i+1, j, Dir.TOP.v, n);
                    unionFind.union(bottom, bottomCellTop);
                }
            }
        }

        Set<Integer> distinctRoots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distinctRoots.add(unionFind.root(index(i, j, Dir.LEFT.v, n)));
                distinctRoots.add(unionFind.root(index(i, j, Dir.BOTTOM.v, n)));
                distinctRoots.add(unionFind.root(index(i, j, Dir.RIGHT.v, n)));
                distinctRoots.add(unionFind.root(index(i, j, Dir.TOP.v, n)));
            }
        }
        return distinctRoots.size();
    }

    private int index(int i, int j, int dir, int n) {
        return dir + (4 * j) + (4 * n * i);
    }
}
