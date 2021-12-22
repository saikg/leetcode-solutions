package io.github.saikg.leetcode.s1284;

import java.util.*;

public class Solution {
    public int minFlips(int[][] mat) {
        if (encode(mat) == 0) {
            return 0;
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> flipsReq = new HashMap<>();
        flipsReq.put(encode(mat), 0);

        Queue<int[][]> traversalQueue = new ArrayDeque<>();
        traversalQueue.add(mat);
        while (!traversalQueue.isEmpty()) {
            int[][] front = traversalQueue.poll();
            int encoded = encode(front);
            int flips = flipsReq.get(encoded);
            visited.add(encoded);
            List<int[][]> neighbours = getNeighbours(front);
            for (int[][] entity: neighbours) {
                int encodedEntity = encode(entity);
                if (encodedEntity == 0) {
                    return flips + 1;
                }
                if (!visited.contains(encodedEntity)) {
                    traversalQueue.add(entity);
                    flipsReq.put(encodedEntity, flips + 1);
                }
            }
        }
        return -1;
    }

    private List<int[][]> getNeighbours(int[][] mat) {
        List<int[][]> neighbours = new ArrayList<>();
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[][] copy = copyMatrix(mat);
                flip(copy, i - 1, j);
                flip(copy, i + 1, j);
                flip(copy, i, j - 1);
                flip(copy, i, j + 1);
                flip(copy, i, j);
                neighbours.add(copy);
            }
        }
        return neighbours;
    }

    private void flip(int[][] mat, int i, int j) {
        if (i >= 0 && i < mat.length && j >= 0 && j < mat[0].length) {
            mat[i][j] = 1 - mat[i][j];
        }
    }

    private int[][] copyMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = mat[i][j];
            }
        }
        return copy;
    }

    private int encode(int[][] mat) {
        int val = 0;
        for (int[] row: mat) {
            for (int el: row) {
                val = val << 1;
                val += el;
            }
        }
        return val;
    }
}