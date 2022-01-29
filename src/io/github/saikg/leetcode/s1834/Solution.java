package io.github.saikg.leetcode.s1834;

import java.util.*;

public class Solution {

    Map<Integer, Integer> processingStartTime;

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] order = new int[n];
        List<Integer> orderList = new ArrayList<>();

        processingStartTime = new HashMap<>();

        Comparator<int[]> queueComparator = Comparator
                .comparingInt((int[] a) -> a[0])
                .thenComparingInt(a -> a[1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>(queueComparator);

        int taskId = 0;
        while (taskId < n) {

            if (queue.isEmpty()) {
                enqueueTask(queue, tasks, taskId, tasks[taskId][0]);
                taskId++;
            } else {
                orderList.addAll(removeFinishedTasks(queue, tasks[taskId][0]));
                // enqueue waiting tasks
                while (!queue.isEmpty() && queue.peek()[2] <= tasks[taskId][0]) {

                }
            }
        }

        return order;
    }

    private void enqueueTask(PriorityQueue<int[]> queue, int[][] tasks, int taskId, int time) {
        int[] task = tasks[taskId];
        queue.add(new int[]{time, time + task[1], taskId});
        processingStartTime.put(taskId, time);
    }

    private List<Integer> removeFinishedTasks(PriorityQueue<int[]> queue, int time) {
        List<Integer> tasks = new ArrayList<>();
        while (!queue.isEmpty() && queue.peek()[0] + queue.peek()[1] <= time) {
            tasks.add(queue.poll()[2]);
        }
        return tasks;
    }
}
