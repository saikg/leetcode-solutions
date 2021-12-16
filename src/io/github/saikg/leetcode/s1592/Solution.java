package io.github.saikg.leetcode.s1592;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    public String reorderSpaces(String text) {
        int spaces = 0, n = text.length();
        for (int i = 0; i < n; i++) {
            spaces += text.charAt(i) == ' ' ? 1 : 0;
        }

        String[] parts = text.trim().split("\\s+");
        if (parts.length == 1) {
            return parts[0] + nSpaces(spaces);
        }

        int gap = spaces / (parts.length - 1);
        int remaining = spaces % (parts.length - 1);
        String delimiter = nSpaces(gap);
        String remainingSpaces = nSpaces(remaining);
        return String.join(delimiter, parts) + remainingSpaces;
    }

    private String nSpaces(int n) {
        return IntStream.range(0, n)
                .boxed()
                .map(v -> " ")
                .collect(Collectors.joining(""));
    }
}
