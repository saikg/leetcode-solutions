package io.github.saikg.leetcode.s1494;

import java.util.*;

public class Solution {

    /*
    * Topological sorting algorithm
    * */
    public int minNumberOfSemesters(int n, int[][] relations, int k) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inorder = new int[n];

        for (int[] relation : relations) {

            int from = relation[0] - 1;
            int to = relation[1] - 1;

            adjList.computeIfAbsent(from, v -> new ArrayList<>()).add(to);
            inorder[to]++;

        }

        Queue<Integer> buffer = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inorder[i] == 0) {
                buffer.add(i);
            }
        }

        int semesters = 0, coursesInSemester = 0;
        Set<Integer> restrictedCourses = new HashSet<>();
        while (!buffer.isEmpty()) {
            int course = buffer.poll();

            if (restrictedCourses.contains(course)) {
                coursesInSemester = 1;
                semesters++;
            } else {
                coursesInSemester++;
            }

            if (coursesInSemester == k) {
                semesters++;
                coursesInSemester = 0;
                restrictedCourses.clear();
            }

            for (int next : adjList.getOrDefault(course, Collections.emptyList())) {
                restrictedCourses.add(next);
                inorder[next]--;
                if (inorder[next] == 0) {
                    buffer.add(next);
                }
            }
        }

        if (coursesInSemester > 0) {
            semesters++;
        }
        return semesters;
    }
}