package io.github.saikg.leetcode.s743;

import java.util.*;

public class Solution {

    class Edge {
        int source;
        int destination;
        int delay;

        Edge(int _source, int _destination, int _delay) {
            source = _source;
            destination = _destination;
            delay = _delay;
        }
    }

    class Pair {
        int node;
        int delay;
        Pair(int _node, int _delay) {
            node = _node;
            delay = _delay;
        }
    }

    public int networkDelayTime(int[][] networkDelay, int machineCount, int source) {
        List<List<Edge>> adjacencyList = buildGraph(networkDelay, machineCount);
        source--;

        int[] timeToReach = new int[machineCount];
        Arrays.fill(timeToReach, Integer.MAX_VALUE);

        PriorityQueue<Pair> traversalQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.delay));

        traversalQueue.add(new Pair(source, 0));
        timeToReach[source] = 0;

        while (!traversalQueue.isEmpty()) {
            Pair prevConnection = traversalQueue.poll();
            int activeNode = prevConnection.node;

            for (Edge connection : adjacencyList.get(activeNode)) {
                int newDelay = prevConnection.delay + connection.delay;
                int nextNode = connection.destination;
                if (timeToReach[nextNode] > newDelay) {
                    traversalQueue.add(new Pair(nextNode, newDelay));
                    timeToReach[nextNode] = newDelay;
                }
            }
        }

        int maximumDelay = 0;
        for (int delay : timeToReach) {
            maximumDelay = Math.max(maximumDelay, delay);
        }
        return maximumDelay == Integer.MAX_VALUE ? -1 : maximumDelay;
    }

    private List<List<Edge>> buildGraph(int[][] networkDelay, int machineCount) {
        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < machineCount; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int edgeNo = 0; edgeNo < networkDelay.length; edgeNo++) {
            int[] connection = networkDelay[edgeNo];
            int source = connection[0] - 1;
            int destination = connection[1] - 1;
            int delay = connection[2];
            adjList.get(source).add(new Edge(source, destination, delay));
        }
        return adjList;
    }
}