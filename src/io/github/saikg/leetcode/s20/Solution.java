package io.github.saikg.leetcode.s20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {

    private static final Map<Character, Character> COMPLEMENTS = new HashMap<>();
    private static final String CLOSING_BRACKETS = "}])";

    static {
        COMPLEMENTS.put(']', '[');
        COMPLEMENTS.put(')', '(');
        COMPLEMENTS.put('}', '{');
    }

    public boolean isValid(String s) {
        Stack<Character> buffer = new Stack<>();
        for (char ch: s.toCharArray()) {
            if (!CLOSING_BRACKETS.contains(String.valueOf(ch))) {
                buffer.push(ch);
                continue;
            }

            if (!buffer.isEmpty() && buffer.peek() == COMPLEMENTS.get(ch)) {
                buffer.pop();
            } else {
                return false;
            }
        }
        return buffer.isEmpty();
    }
}