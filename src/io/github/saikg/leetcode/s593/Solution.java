package io.github.saikg.leetcode.s593;

import java.util.Arrays;

public class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = new int[][]{
                p1, p2, p3, p4
        };

        int n = points.length, k = 0;
        double[] distances = new double[6];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                distances[k++] = distance(points[i], points[j]);
            }
        }
        Arrays.sort(distances);

        double sideLength = distances[0];
        int count = 0;
        for (double d: distances) {
            count += (d == sideLength) ? 1 : 0;
        }
        return (count == 4) && (distances[4] == distances[5]);
    }

    private double distance(int[] p1, int[] p2) {
        long dx = Math.abs(p1[0] - p2[0]);
        long dy = Math.abs(p1[1] - p2[1]);
        return Math.sqrt(dx*dx + dy*dy);
    }
}