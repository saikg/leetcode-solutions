package io.github.saikg.leetcode.s497;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Solution {

    private static final Random random = new Random();

    int[][] rects;
    TreeMap<Integer, Integer> weightedRectangleIndex = new TreeMap<>();
    int totalArea;

    public Solution(int[][] rects) {
        int n = rects.length;
        this.rects = rects;

        int index = 0, cumulativeArea = 0;
        for (int[] rect : rects) {
            weightedRectangleIndex.put(cumulativeArea, index++);
            cumulativeArea += area(rect);
        }
        totalArea = cumulativeArea;
    }

    public int[] pick() {
        int randomArea = random.nextInt(totalArea);
        Map.Entry<Integer, Integer> entry = weightedRectangleIndex.floorEntry(randomArea);
        int rectPoint = randomArea - entry.getKey();
        int[] rect = rects[entry.getValue()];

        return new int[]{
                rect[0] + rectPoint % (rect[2] - rect[0]),
                rect[1] + rectPoint / (rect[2] - rect[0])
        };
    }

    private int area(int[] rect) {
        int dx = rect[2] - rect[0];
        int dy = rect[3] - rect[1];
        return Math.abs(dx * dy);
    }
}