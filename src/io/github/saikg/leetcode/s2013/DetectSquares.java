package io.github.saikg.leetcode.s2013;

import java.util.*;

public class DetectSquares {

    Map<Integer, Set<Integer>> yIndex = new HashMap<>();
    Map<String, Integer> count = new HashMap<>();

    public DetectSquares() {}

    public void add(int[] point) {
        String hash = hashPoint(point);
        int v = count.getOrDefault(hash, 0);
        count.put(hash, v + 1);
        yIndex.computeIfAbsent(point[1], t -> new HashSet<>()).add(point[0]);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];

        Set<Integer> otherPointsX = yIndex.getOrDefault(y, Collections.emptySet());
        int ans = 0;
        for (int px: otherPointsX) {
            int side = Math.abs(x - px);
            if (side == 0) {
                continue;
            }
            int[] p1 = point;
            int[] p2 = {px, y};
            int[] p3 = {px, y + side};
            int[] p4 = {x, y + side};
            int[] p5 = {x, y - side};
            int[] p6 = {px, y - side};
            ans += numberOfWays(p2, p3, p4);
            ans += numberOfWays(p2, p5, p6);
        }
        return ans;
    }

    private int numberOfWays(int[] p2, int[] p3, int[] p4) {
        int ans = 1;
        ans *= count.getOrDefault(hashPoint(p2), 0);
        ans *= count.getOrDefault(hashPoint(p3), 0);
        ans *= count.getOrDefault(hashPoint(p4), 0);
        return ans;
    }

    private String hashPoint(int[] p) {
        return p[0] + "," + p[1];
    }
}