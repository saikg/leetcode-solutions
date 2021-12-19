package io.github.saikg.leetcode.s394;

import java.util.Stack;

public class Solution {

    public String decodeString(String s) {
        int n = s.length();
        Stack<Character> buffer = new Stack<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch != ']') {
                buffer.push(ch);
                continue;
            }

            StringBuilder sb = new StringBuilder();
            while (buffer.peek() != '[') {
                sb.append(buffer.pop());
            }
            buffer.pop(); // pop [

            StringBuilder multiplierString = new StringBuilder();
            while (!buffer.isEmpty() && isDigit(buffer.peek())) {
                multiplierString.append(buffer.pop());
            }
            int multiplier = Integer.parseInt(multiplierString.reverse().toString());

            String str = sb.toString();
            for (int j = 0; j < multiplier-1; j++) {
                sb.append(str);
            }

            for (char j: sb.reverse().toString().toCharArray()) {
                buffer.push(j);
            }
        }

        StringBuilder decodedString = new StringBuilder();
        while (!buffer.isEmpty()) {
            decodedString.append(buffer.pop());
        }
        return decodedString.reverse().toString();
    }

    private boolean isDigit(char ch) {
        return (ch - '0' >= 0) && ('9' - ch >= 0);
    }

    private boolean isLetter(char ch) {
        return (ch - 'a' >= 0) && ('z' - ch >= 0);
    }

    public static void main(String[] args) {
        String testInput = "100[leetcode]";
        Solution solution = new Solution();
        String ans = solution.decodeString(testInput);
        System.out.println("ans = " + ans);
    }
}