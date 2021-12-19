package io.github.saikg.leetcode.s150;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solution {

    Set<String> operators = new HashSet<String>(){{
        add("+");
        add("-");
        add("*");
        add("/");
    }};

    public int evalRPN(String[] tokens) {
        Stack<String> tokenBuffer = new Stack<>();
        for (String token: tokens) {
            if (operators.contains(token)) {
                String token2 = tokenBuffer.pop();
                String token1 = tokenBuffer.pop();
                Integer op1 = Integer.parseInt(token1);
                Integer op2 = Integer.parseInt(token2);
                Integer result = evaluate(op1, op2, token);
                tokenBuffer.push(result.toString());
            } else {
                tokenBuffer.push(token);
            }
        }
        return Integer.parseInt(tokenBuffer.pop());
    }

    private int evaluate(int op1, int op2, String operator) {
        switch (operator) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
            default:
                throw new IllegalArgumentException("invalid operator provided");
        }
    }
}