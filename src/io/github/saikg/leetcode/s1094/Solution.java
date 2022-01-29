package io.github.saikg.leetcode.s1094;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        return bucketSortImpl(trips, capacity);
    }

    // bucket-sort implementation
    // Space complexity: O(M); where M is the range of numbers
    // Time complexity: O(max(N, M)); where N is number of trips
    // works better when range of trips is small.
    private boolean bucketSortImpl(int[][] trips, int capacity) {
        int timeRange = 1001;
        int[] occupancyAtTimestamp = new int[timeRange];
        for (int[] trip : trips) {
            occupancyAtTimestamp[trip[1]] += trip[0];
            occupancyAtTimestamp[trip[2]] -= trip[0];
        }

        int occupied = 0;
        for (int occupancy : occupancyAtTimestamp) {
            occupied += occupancy;
            if (occupied > capacity) {
                return false;
            }
        }
        return true;
    }

    // priority-queue implementation
    // Space complexity: O(N);
    // Time complexity: O(NlogN);
    // works better when range of trips is big.
    private boolean priorityQueueImpl(int[][] trips, int capacity) {
        List<int[]> events = new ArrayList<>();
        for (int[] trip : trips) {
            events.add(new int[]{
                    trip[0], trip[2]
            });
            events.add(new int[]{
                    trip[1], -trip[2]
            });
        }

        events.sort(Comparator
                .comparingInt((int[] a) -> a[0])
                .thenComparingInt(a -> a[1])
        );

        int availableCap = capacity;
        for (int[] event : events) {
            availableCap -= event[1];
            if (availableCap < 0) {
                return false;
            }
        }
        return true;
    }
}