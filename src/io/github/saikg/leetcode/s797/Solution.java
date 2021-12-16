package io.github.saikg.leetcode.s797;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<List<Integer>> paths;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int source = 0;
        paths = new ArrayList<>();
        traverse(graph, source, new ArrayList<>());
        return paths;
    }

    void traverse(int[][] graph, int node, List<Integer> path) {
        path.add(node);
        if (node == graph.length - 1) {
            paths.add(new ArrayList<>(path));
        } else {
            for (int v: graph[node]) {
                traverse(graph, v, path);
            }
        }
        path.remove(path.size() - 1);
    }
}