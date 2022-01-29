package io.github.saikg.leetcode.s133;

import java.util.*;

public class Solution {
    public Node cloneGraph(Node node) {

        Map<Node, Node> oldToNewMap = new HashMap<>();
        oldToNewMap.put(node, cloneNode(node));

        // BFS traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Set<Node> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node front = queue.poll();
            Node copy = oldToNewMap.get(front); // should always exist at this point

            if (visited.contains(front)) {
                continue;
            }
            visited.add(front);

            for (Node neighbour : front.neighbors) {
                oldToNewMap.computeIfAbsent(neighbour, v -> cloneNode(neighbour));
                copy.neighbors.add(oldToNewMap.get(neighbour));
                queue.add(neighbour);
            }
        }

        return oldToNewMap.get(node);
    }

    private Node cloneNode(Node node) {
        return new Node(node.val, new ArrayList<>());
    }
}