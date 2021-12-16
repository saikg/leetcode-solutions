package io.github.saikg.leetcode.s1552;

import java.util.Arrays;

public class Solution {
    public int maxDistance(int[] position, int m) {
        int n = position.length;
        Arrays.sort(position);
        int minDistance = 0, maxDistance = position[n-1] - position[0];
        while (minDistance <= maxDistance) {
            int distance = (minDistance + maxDistance) / 2;
            boolean possible = arrange(position, m, distance);
            System.out.printf("[min,d,max] = [%d,%d,%d]\n", minDistance, distance, maxDistance);
            if (possible) {
                minDistance = distance + 1;
            } else {
                maxDistance = distance - 1;
            }
        }
        return maxDistance;
    }

    private boolean arrange(int[] position, int m, int dist) {
        int item = 1, lastPos = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPos >= dist) {
                lastPos = position[i];
                item++;
            }
        }
        return item >= m;
    }

    public static void main(String[] args) {
        int[] position = new int[]{5,4,3,2,1,1000000000};
        int m = 2;

        Solution solution = new Solution();
        int ans = solution.maxDistance(position, m);
        System.out.println("ans = " + ans);
    }
}
