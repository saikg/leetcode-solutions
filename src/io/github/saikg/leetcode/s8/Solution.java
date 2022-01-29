package io.github.saikg.leetcode.s8;

public class Solution {
    public int myAtoi(String s) {
        int result = 0;

        s = s.trim();
        if (s.isEmpty()) {
            return result;
        }

        char first = s.charAt(0);
        boolean negative = first == '-';
        boolean positive = first == '+';
        int sign = negative ? -1 : 1;
        int idx = (negative || positive ? 1 : 0);
        while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
            int change = (s.charAt(idx++) - '0') * sign;
            result = add(result, change);
        }
        return result;
    }

    private int add(int a, int b) {
        long la = a * 10L;
        long lb = b;
        if (la + lb > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (la + lb < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int)(la + lb);
        }
    }
}