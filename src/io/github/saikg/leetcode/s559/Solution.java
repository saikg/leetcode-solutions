package io.github.saikg.leetcode.s559;

import io.github.saikg.leetcode.common.narytree.Node;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Solution {
    public int maxDepth(Node root) {
        Queue<Node> queue = new LinkedList<>();
        int level = 0;
        if (root == null) {
            return level;
        }

        queue.add(root);
        while (!queue.isEmpty()) {
            int nodesInLevel = queue.size();
            for (int i = 0; i < nodesInLevel; i++) {
                Node node = queue.poll();
                node.children.stream()
                        .filter(Objects::nonNull)
                        .forEach(queue::add);
            }
            level++;
        }
        return level;
    }
}