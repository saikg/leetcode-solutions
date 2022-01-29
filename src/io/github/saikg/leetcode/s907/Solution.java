package io.github.saikg.leetcode.s907;

import java.util.*;

public class Solution {

    private static final int MOD = 1_000_000_007;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = i - stack.peek();
            stack.push(i);
        }

        stack.clear();
        stack.push(n);

        int[] right = new int[n];
        for (int i = n-1; i >= 0; i--) {
            while (stack.peek() != n && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.peek() - i;
            stack.push(i);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            int v = multiply(left[i], right[i]);
            v = multiply(v, arr[i]);
            sum = add(sum, v);
        }
        return sum;
    }

    private int add(int a, int b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    private int multiply(int a, int b) {
        long la = a % MOD;
        long lb = b % MOD;
        return (int)((la * lb) % (long)MOD);
    }
}