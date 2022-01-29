package io.github.saikg.leetcode.algo.monotonestack;

import java.util.Stack;

public class Histogram {

    public int maxRectangle(int[] heights) {
        int height, width, maxArea = 0;

        Stack<Integer> indices = new Stack<>();
        indices.push(-1);
        for (int i = 0; i < heights.length; i++) {
            while (indices.peek() != -1 && heights[indices.peek()] > heights[i]) {
                height = heights[indices.pop()];
                width = i - indices.peek() - 1;
                maxArea = Math.max(width * height, maxArea);
            }
            indices.push(i);
        }

        while (indices.peek() != -1) {
            height = heights[indices.pop()];
            width = heights.length - indices.peek() - 1;
            maxArea = Math.max(width * height, maxArea);
        }

        return maxArea;
    }

}
