package io.github.saikg.leetcode.s1762;

import java.util.*;

public class Solution {
    public int[] findBuildings(int[] heights) {
        //return monotonicStackImpl(heights);
        return maxHeightImpl(heights);
    }

    /*
    * max height building blocks the view of all buildings behind
    * Space complexity: O(1)
    * Time complexity: O(N)
    * */
    private int[] maxHeightImpl(int[] heights) {
        int n = heights.length, maxHeight = 0;
        Stack<Integer> result = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            if (maxHeight < heights[i]) {
                maxHeight = heights[i];
                result.add(i);
            }
        }

        int m = result.size();
        int[] buildingsWithView = new int[m];
        for (int i = 0; i < m; i++) {
            buildingsWithView[i] = result.pop();
        }
        return buildingsWithView;
    }

    /*
    * uses monotonic stack to track the buildings with view
    * Space complexity: O(N)
    * Time complexity: O(N)
    * */
    private int[] monotonicStackImpl(int[] heights) {
        int n = heights.length;
        List<Integer> result = new ArrayList<>();
        Stack<Integer> buildingsBuffer = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            while (!buildingsBuffer.isEmpty() && heights[buildingsBuffer.peek()] < heights[i]) {
                buildingsBuffer.pop();
            }
            if (buildingsBuffer.isEmpty()) {
                result.add(i);
            }
            buildingsBuffer.add(i);
        }
        int[] buildingsWithView = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            buildingsWithView[i] = result.get(result.size()-1-i);
        }
        return buildingsWithView;
    }
}