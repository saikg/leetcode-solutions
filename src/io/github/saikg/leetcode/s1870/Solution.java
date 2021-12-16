package io.github.saikg.leetcode.s1870;

public class Solution {

    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }

        int low = 1, high = 10000000;
        while (low <= high) {
            int mid = (low + high) / 2;
            double time = timeTaken(dist, mid);
            if (time > hour) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private double timeTaken(int[] dist, int speed) {
        double time = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            time += Math.ceil((double)dist[i] / speed);
        }
        time += (double) dist[dist.length - 1] / speed;
        return time;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.minSpeedOnTime(new int[]{1, 3, 2}, 2.7);
        System.out.println("ans = " + ans);
    }
}
