package io.github.saikg.leetcode.s84;

import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        Solver solver;
        solver = new DivideAndConquerImpl();
        return solver.solve(heights);
    }

    static interface Solver {
        int solve(int[] heights);
    }

    /*
     * Monotonic stack implementation
     * Space complexity: O(N);
     * Time complexity: O(N);
     * where N is the size of heights
     * */
    static class MonotonicStackImpl implements Solver {
        public int solve(int[] heights) {
            int n = heights.length;
            int maxArea = 0;
            Stack<Integer> positionOfTower = new Stack<>();
            positionOfTower.push(-1);
            for (int pos = 0; pos < n; pos++) {
                while (positionOfTower.peek() != -1 && heights[positionOfTower.peek()] >= heights[pos]) {
                    int lastTowerPos = positionOfTower.pop();
                    int area = heights[lastTowerPos] * (pos - positionOfTower.peek() - 1);
                    maxArea = Math.max(area, maxArea);
                }
                positionOfTower.push(pos);
            }
            while (positionOfTower.peek() != -1) {
                int height = heights[positionOfTower.pop()];
                int width = n - positionOfTower.peek() - 1;
                maxArea = Math.max(height * width, maxArea);
            }
            return maxArea;
        }
    }

    /*
    * Divide and conquer implementation
    * Space complexity: O(N)
    * Time complexity: O(NlogN)
    * */
    static class DivideAndConquerImpl implements Solver {

        MinIndexSegmentTree segmentTree;

        public int solve(int[] heights) {
            segmentTree = new MinIndexSegmentTree(heights);
            return calculateArea(heights, 0, heights.length - 1);
        }

        private int calculateArea(int[] heights, int begin, int end) {
            if (begin > end) {
                return 0;
            }

            int minIndex = segmentTree.query(1, 0, heights.length - 1, begin, end);
            int leftArea = calculateArea(heights, begin, minIndex - 1);
            int rightArea = calculateArea(heights, minIndex + 1, end);
            int curr = heights[minIndex] * (end - begin + 1);
            return Math.max(curr, Math.max(leftArea, rightArea));
        }
    }

    /*
    * brute force implementation
    * Space complexity: O(1)
    * Time complexity: O(N^2)
    * */
    static class BruteForceImpl implements Solver {
        public int solve(int[] heights) {
            int n = heights.length;
            int maxArea = 0;
            for (int i = 0; i < n; i++) {
                int minHeight = heights[i];
                for (int j = i; j < n; j++) {
                    minHeight = Math.min(heights[i], minHeight);
                    maxArea = Math.max(maxArea, minHeight * (j - i + 1));
                }
            }
            return maxArea;
        }
    }

    static class MinIndexSegmentTree {

        int n;
        int[] heights;
        int[] tree;

        MinIndexSegmentTree(int[] heights) {
            n = heights.length;
            this.heights = heights;
            this.tree = new int[4*n];
            build(1, 0, n-1);
        }

        private int build(int treeIdx, int begin, int end) {
            if (begin == end) {
                // each element is min for [begin, begin] range
                tree[treeIdx] = begin;
                return begin;
            }

            int mid = (begin + end) / 2;
            int left = build(2*treeIdx, begin, mid);
            int right = build(2*treeIdx+1, mid + 1, end);

            if (heights[left] < heights[right]) {
                tree[treeIdx] = left;
                return left;
            } else {
                tree[treeIdx] = right;
                return right;
            }
        }

        public int query(int treeIdx,
                         int rangeBegin, int rangeEnd,
                         int queryBegin, int queryEnd) {
            // current range lies within the query range
            if (queryBegin <= rangeBegin && rangeEnd <= queryEnd) {
                return tree[treeIdx];
            }
            // current range lies outside the query range
            if (rangeEnd < queryBegin || queryEnd < rangeBegin) {
                return -1;
            }

            int mid = (rangeBegin + rangeEnd) / 2;
            int left = query(2*treeIdx, rangeBegin, mid, queryBegin, queryEnd);
            int right = query(2*treeIdx + 1, mid + 1, rangeEnd, queryBegin, queryEnd);

            // return index with lower value
            if (left == -1) {
                return right;
            } else if (right == -1) {
                return left;
            } else {
                return heights[right] < heights[left] ? right : left;
            }
        }
    }
}