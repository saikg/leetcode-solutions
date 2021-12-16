package io.github.saikg.leetcode.s56;

import java.util.*;

public class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        LinkedList<int[]> stack = new LinkedList<>();
        for (int[] interval: intervals) {
            if (stack.isEmpty()) {
                stack.add(interval);
                continue;
            }
            int[] top = stack.removeLast();
            if (top[1] >= interval[0]) {
                top[1] = Math.max(top[1], interval[1]);
                stack.addLast(top);
            } else {
                stack.addLast(top);
                stack.addLast(interval);
            }
        }
        return stack.toArray(new int[stack.size()][]);
    }
}