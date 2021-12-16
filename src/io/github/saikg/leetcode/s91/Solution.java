package io.github.saikg.leetcode.s91;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<String> possibleDecodings;

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        possibleDecodings = new ArrayList<>();
        backtrack(0, s, new StringBuilder());
        return possibleDecodings.size();
    }

    private void backtrack(int k, String s, StringBuilder decoding) {
        if (k == s.length()) {
            possibleDecodings.add(decoding.toString());
            return;
        }

        if (s.charAt(k) == '0') {
            return;
        }

        decoding.append(getCharForOffset(s.charAt(k)));
        backtrack(k + 1, s, decoding);
    }

    private char getCharForOffset(int offset) {
        return (char)('a' + (offset - 1));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getCharForOffset(2));
    }
}