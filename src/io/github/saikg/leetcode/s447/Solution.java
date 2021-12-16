package io.github.saikg.leetcode.s447;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;

        Map<Long, List<int[][]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long d = distance(points[i], points[j]);
                map.computeIfAbsent(d, nr -> new ArrayList<>())
                        .add(new int[][]{
                                points[i], points[j]
                        });
            }
        }

        int ans = 0;
        for (long dist: map.keySet()) {
            Map<int[], List<int[]>> equidistantPoints = new HashMap<>();
            for (int[][] pset: map.get(dist)) {
                equidistantPoints
                        .computeIfAbsent(pset[0], v -> new ArrayList<>())
                        .add(pset[1]);
                equidistantPoints
                        .computeIfAbsent(pset[1], v -> new ArrayList<>())
                        .add(pset[0]);
            }

            for (int[] point: equidistantPoints.keySet()) {
                int t = equidistantPoints.size();
                ans += t * (t-1);
            }
        }
        return ans;
    }

    // O(n^3) TLE
    private int naive(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = 0; k < n; k++) {
                    if (i == k || j == k) continue;
                    if (distance(points[i], points[j]) == distance(points[i], points[k])) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private long distance(int[] pointA, int[] pointB) {
        long dx = pointA[0] - pointB[0];
        long dy = pointA[1] - pointB[1];
        return dx*dx + dy*dy;
    }
}