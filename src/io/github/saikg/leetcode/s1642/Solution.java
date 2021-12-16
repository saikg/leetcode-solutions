package io.github.saikg.leetcode.s1642;

import java.util.PriorityQueue;

public class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        PriorityQueue<Integer> maxJumps = new PriorityQueue<>(ladders);
        int bricksReq = 0;
        for (int i = 0; i < n-1; i++) {
            int jump = heights[i+1] - heights[i];
            if (jump <= 0) {
                continue;
            }
            maxJumps.add(jump);
            if (maxJumps.size() > ladders) {
                bricksReq += maxJumps.remove();
            }
            if (bricksReq > bricks) {
                return i;
            }
        }
        return n-1;
    }
}
