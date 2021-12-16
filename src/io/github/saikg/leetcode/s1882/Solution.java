package io.github.saikg.leetcode.s1882;

import java.util.*;

public class Solution {

    class Server {
        int weight;
        int idx;
        int release;
        Server(int wt, int i) {
            weight = wt;
            idx = i;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int m = servers.length;
        int n = tasks.length;

        PriorityQueue<Server> freeServers = new PriorityQueue<>((s1, s2) -> {
            if (Integer.compare(s1.weight, s2.weight) != 0) {
                return Integer.compare(s1.weight, s2.weight);
            }
            return Integer.compare(s1.idx, s2.idx);
        });
        PriorityQueue<Server> busyServers = new PriorityQueue<>((s1, s2) -> {
            return Integer.compare(s1.release, s2.release);
        });

        for (int i = 0; i < m; i++) {
            freeServers.add(new Server(servers[i], i));
        }

        int[] assignedServers = new int[n];
        int time = 0, taskId = 0;
        while (taskId < n) {

            while (!busyServers.isEmpty() && busyServers.peek().release <= time) {
                freeServers.add(busyServers.poll());
            }

            while (!freeServers.isEmpty() && taskId <= time && taskId < n) {
                Server server = freeServers.poll();
                server.release = time + tasks[taskId];
                busyServers.add(server);
                assignedServers[taskId++] = server.idx;
            }

            if (freeServers.isEmpty())
                time = busyServers.peek().release;
            else
                time++;
        }

        return assignedServers;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] servers = new int[]{338,890,301,532,284,930,426,616,919,267,571,140,716,859,980,469,628,490,195,664,925,652,503,301,917,563,82,947,910,451,366,190,253,516,503,721,889,964,506,914,986,718,520,328,341,765,922,139,911,578,86,435,824,321,942,215,147,985,619,865};
        int[] tasks = new int[]{773,537,46,317,233,34,712,625,336,221,145,227,194,693,981,861,317,308,400,2,391,12,626,265,710,792,620,416,267,611,875,361,494,128,133,157,638,632,2,158,428,284,847,431,94,782,888,44,117,489,222,932,494,948,405,44,185,587,738,164,356,783,276,547,605,609,930,847,39,579,768,59,976,790,612,196,865,149,975,28,653,417,539,131,220,325,252,160,761,226,629,317,185,42,713,142,130,695,944,40,700,122,992,33,30,136,773,124,203,384,910,214,536,767,859,478,96,172,398,146,713,80,235,176,876,983,363,646,166,928,232,699,504,612,918,406,42,931,647,795,139,933,746,51,63,359,303,752,799,836,50,854,161,87,346,507,468,651,32,717,279,139,851,178,934,233,876,797,701,505,878,731,468,884,87,921,782,788,803,994,67,905,309,2,85,200,368,672,995,128,734,157,157,814,327,31,556,394,47,53,755,721,159,843};
        int[] ans = solution.assignTasks(servers, tasks);
        System.out.println(Arrays.toString(ans));
    }
}
