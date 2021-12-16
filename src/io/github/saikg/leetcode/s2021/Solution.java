package io.github.saikg.leetcode.s2021;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public int brightestPosition(int[][] lights) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> {
            int v = Integer.compare(a.pos, b.pos);
            if (v != 0) {
                return v;
            }
            return Integer.compare(b.op, a.op);
        });

        int minPos = Integer.MAX_VALUE, maxPos = Integer.MIN_VALUE;
        for (int[] light: lights) {
            int pos = light[0];
            int range = light[1];
            int min = light[0] - light[1];
            int max = light[0] + light[1];

            queue.add(new Node(min, 'o'));
            queue.add(new Node(max, 'e'));

            minPos = Math.min(min, minPos);
            maxPos = Math.max(max, maxPos);
        }

        int maxBrightness = 0, mbPosition = minPos - 1;
        int brightness = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.op == 'o') {
                brightness++;
            } else {
                brightness--;
            }
            if (brightness > maxBrightness) {
                maxBrightness = brightness;
                mbPosition = node.pos;
            }
        }
        return mbPosition;
    }

    class Node {
        int pos;
        char op;
        Node(int p, char o) {
            pos = p;
            op = o;
        }
    }
}