package io.github.saikg.leetcode.s1249;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solution {
    public String minRemoveToMakeValid(String s) {

        int n = s.length();

        Set<Integer> invalidIndices = new HashSet<>();
        Stack<Integer> buffer = new Stack<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (Character.isAlphabetic(ch)) {
                continue;
            }

            if (ch == ')') {
                if (!buffer.isEmpty() && s.charAt(buffer.peek()) == '(') {
                    buffer.pop();
                } else {
                    invalidIndices.add(i);
                }
            } else {
                buffer.push(i);
            }
        }

        while (!buffer.isEmpty()) {
            invalidIndices.add(buffer.pop());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!invalidIndices.contains(i)) {
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}