package io.github.saikg.leetcode.s1235;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    class Job {
        int start;
        int end;
        int profit;
        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        Comparator<Job> jobComparator = Comparator
                .comparingInt((Job a) -> a.start);
        jobs.sort(jobComparator);

        return getMaxProfit(jobs);
    }

    private int getMaxProfit(List<Job> jobs) {
        int n = jobs.size();
        int[] dp = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int profit = 0;
            int nextIndex = findNextJob(jobs, jobs.get(i).end);

            if (nextIndex != n) {
                profit = jobs.get(i).profit + dp[nextIndex];
            } else {
                profit = jobs.get(i).profit;
            }

            if (i == n-1) {
                dp[i] = profit;
            } else {
                dp[i] = Math.max(profit, dp[i + 1]);
            }
        }
        return dp[0];
    }

    private int findNextJob(List<Job> jobs, int lastEndTime) {
        int start = 0, end = jobs.size() - 1, nextIndex = jobs.size();

        while (start <= end) {
            int mid = (start + end) / 2;
            if (jobs.get(mid).start >= lastEndTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }
}