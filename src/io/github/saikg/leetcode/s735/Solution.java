package io.github.saikg.leetcode.s735;

import java.util.Stack;

public class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> buffer = new Stack<>();

        int i = 0;
        while (i < n) {
            int asteroid = asteroids[i];
            if (asteroid > 0 || buffer.isEmpty() || sameSign(buffer.peek(), asteroid)) {
                buffer.push(asteroid);
                i++;
                continue;
            }

            if (!buffer.isEmpty() && buffer.peek() > 0) {
                int resultant = buffer.peek() + asteroid;
                if (resultant < 0) {
                    buffer.pop();
                } else if (resultant == 0) {
                    buffer.pop();
                    i++;
                } else {
                    i++;
                }
            }
        }

        int[] postCollision = new int[buffer.size()];
        int id = buffer.size() - 1;
        while (!buffer.isEmpty()) {
            postCollision[id--] = buffer.pop();
        }
        return postCollision;
    }

    private boolean sameSign(int a, int b) {
        return (a >= 0) ^ (b < 0);
    }
}