package io.github.saikg.leetcode.s227;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int calculate(String s) {
        List<String> equation = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        Character prevOp = null;
        for (char ch : s.toCharArray()) {
            if (ch == ' ') continue;
            if (Character.isDigit(ch)) {
                token.append(ch);
            } else {
                equation.add(token.toString());
                token.setLength(0);
                if (equation.size() >= 3 && "*/".contains(String.valueOf(prevOp))) {
                    computeLastOperation(equation);
                }

                equation.add(String.valueOf(ch));
                prevOp = ch;
            }
        }
        equation.add(token.toString());
        if (equation.size() >= 3 && "*/".contains(String.valueOf(prevOp))) {
            computeLastOperation(equation);
        }

        String lastOp = null;
        Integer ans = null;
        for (int i = 0; i < equation.size(); i++) {
            String v = equation.get(i);
            if ("+-".contains(v)) {
                lastOp = v;
            } else {
                if (ans == null) {
                    ans = Integer.parseInt(v);
                } else {
                    ans = compute(ans, Integer.parseInt(v), lastOp);
                }
            }
        }

        System.out.println(equation);

        return ans;
    }

    void computeLastOperation(List<String> equation) {
        int v = equation.size();
        int op2 = Integer.parseInt(equation.remove(v - 1));
        String operation = equation.remove(v - 2);
        int op1 = Integer.parseInt(equation.remove(v - 3));
        equation.add(Integer.toString(compute(op1, op2, operation)));
    }

    int compute(int a, int b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return -1;
        }
    }
}