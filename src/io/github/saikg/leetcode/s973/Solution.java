package io.github.saikg.leetcode.s973;

import java.util.Arrays;

public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = hypot(points[i][0], points[i][1]);
        }
        quickSelect(points, distances, k, 0, n-1);

        return Arrays.copyOf(points, k);
    }

    int hypot(int x, int y) {
        return x*x + y*y;
    }

    void quickSelect(int[][] points, int[] distances, int k, int left, int right) {
        int pivot = partition(points, distances, left, right);
        if (pivot == k - 1) {
            return;
        } else if (pivot > k) {
            quickSelect(points, distances, k, left, pivot - 1);
        } else {
            quickSelect(points, distances, k, pivot + 1, right);
        }
    }

    int partition(int[][] points, int[] distances, int left, int right) {
        if (left == right) {
            return left;
        }

        int pivotLoc = left, comp = right;
        for (int i = left; i < right; i++) {
            if (distances[i] < distances[comp]) {
                swap(points, i, pivotLoc);
                swap(distances, i, pivotLoc++);
            }
        }

        int[] p = points[comp];
        points[right] = points[pivotLoc];
        points[pivotLoc] = p;
        return pivotLoc;
    }

    void swap(int[][] points, int a, int b) {
        int[] point = points[a];
        points[a] = points[b];
        points[b] = point;
    }

    void swap(int[] distances, int a, int b) {
        int d = distances[a];
        distances[a] = distances[b];
        distances[b] = d;
    }

    public static void main(String[] args) {
        int[][] points = {
                {0, 0}, {5, 4}, {1, 2}, {1, 1}, {3, 0}
        };
        int k = 5;

        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.kClosest(points, k)));
    }
}