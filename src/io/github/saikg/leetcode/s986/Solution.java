package io.github.saikg.leetcode.s986;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length, n = secondList.length;

        List<int[]> intersections = new ArrayList<>();
        int ida = 0, idb = 0;
        while (ida < m && idb < n) {
            int[] interval1 = firstList[ida];
            int[] interval2 = secondList[idb];
            int[] intersection = overlap(interval1, interval2);
            if (intersection.length > 0) {
                intersections.add(intersection);
            }
            if (interval1[1] < interval2[1]) {
                ida++;
            } else {
                idb++;
            }
        }

        int[][] result = new int[intersections.size()][2];
        for (int i = 0; i < intersections.size(); i++) {
            result[i] = intersections.get(i);
        }
        return result;
    }

    private int[] overlap(int[] a, int[] b) {
        if (withinRange(a[1], b)) {
            return new int[]{
                    Math.max(a[0], b[0]), a[1]
            };
        }
        if (withinRange(b[1], a)) {
            return new int[] {
                    Math.max(a[0], b[0]), b[1]
            };
        }
        return new int[]{};

    }

    private boolean withinRange(int a, int [] range) {
        return range[0] <= a && a <= range[1];
    }
}