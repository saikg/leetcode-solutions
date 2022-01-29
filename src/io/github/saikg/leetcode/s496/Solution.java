package io.github.saikg.leetcode.s496;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums2.length;
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> buffer = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            while (!buffer.isEmpty() && buffer.peek() < nums2[i]) {
                buffer.pop();
            }
            map.put(nums2[i], buffer.isEmpty() ? -1 : buffer.peek());
            buffer.add(nums2[i]);
        }

        int [] indices = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            indices[i] = map.get(nums1[i]);
        }
        return indices;
    }
}