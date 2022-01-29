package io.github.saikg.leetcode.s452;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        int shots = 0;
        System.out.println(Arrays.deepToString(points));
        return shots;
    }
}