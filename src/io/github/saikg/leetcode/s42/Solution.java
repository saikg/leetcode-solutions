package io.github.saikg.leetcode.s42;

import java.util.Stack;

public class Solution {
    public int trap(int[] height) {
        int water = 0;
        Stack<Integer> monotonicStack = new Stack<>();
        int intermediate = 0;
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            while (!monotonicStack.isEmpty() && height[monotonicStack.lastElement()] <= h) {
                int last = monotonicStack.lastElement();
                int lastH = height[last];
                water += lastH * (i - last);
            }
            if (monotonicStack.isEmpty()) {
                monotonicStack.add(h);
                continue;
            }
            int last = monotonicStack.lastElement();
            int lastH = height[last];
            if (h > lastH) {
                monotonicStack.add(i);
                water += lastH * (i - last);
            } else {
                intermediate += h;
            }
        }
        return 1;
    }
}