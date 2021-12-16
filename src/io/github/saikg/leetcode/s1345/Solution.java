package io.github.saikg.leetcode.s1345;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> sameValueNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sameValueNodes.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        Map<Integer, Integer> jumpsReq = new HashMap<>();
        Queue<Integer> traversalQueue = new ArrayDeque<>();
        Set<Integer> enqueuedNums = new HashSet<>();
        boolean[] visited = new boolean[n];
        jumpsReq.put(0, 0);
        traversalQueue.offer(0);
        while (!traversalQueue.isEmpty()) {
            int idx = traversalQueue.poll();
            int jumps = jumpsReq.get(idx);

            if (enqueuedNums.contains(arr[idx])) {
                continue;
            }
            enqueuedNums.add(arr[idx]);

            List<Integer> neighbours = sameValueNodes.get(arr[idx]);
            neighbours.add(idx - 1);
            neighbours.add(idx + 1);
            neighbours = neighbours.stream()
                    .filter(v -> v >= 0 &&  v < n && v != idx)
                    .collect(Collectors.toList());

            for (int u: neighbours) {
                if (u == n-1) {
                    return jumps + 1;
                }
                if (visited[u] || u == idx) {
                    continue;
                }
                jumpsReq.put(u, jumps + 1);
                traversalQueue.add(u);
                visited[u] = true;
            }
        }
        System.out.println(jumpsReq);
        return jumpsReq.get(n-1);
    }

    public static void main(String[] args) {
        int[] arr = new int[50000];
        Arrays.fill(arr, 7);
        arr[49999] = 11;
        Solution solution = new Solution();
        int ans = solution.minJumps(arr);
        System.out.println(ans);
    }
}