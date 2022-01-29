package io.github.saikg.leetcode.s418;

import java.util.Arrays;

public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int count = 0;
        int longestWordLength = Arrays.stream(sentence)
                .map(String::length)
                .reduce(Math::max).get();

        if (longestWordLength > cols) {
            return count;
        }

        int[][] space = new int[cols][2];
        for (int col = 0; col < cols; col++) {
            int rowId = 0, remaining = cols - col;
            for (int idx = 0; idx < sentence.length; idx++) {
                if (remaining < sentence[idx].length()) {
                    rowId++; remaining = cols;
                }
                remaining -= sentence[idx].length() + 1; //extra space
                remaining = Math.max(remaining, 0);
            }
            space[col][0] = rowId;
            space[col][1] = cols - remaining;
        }

        int row = 0, col = 0;
        while (row < rows) {
            row += space[col][0];
            col = space[col][1];

            if (row < rows && col <= cols) {
                count++;
            }

            if (col == cols) {
                row++;
                col = 0;

                if (row < rows) {
                    count = (rows / row) * count;
                    row = rows - (rows % row);
                }
            }
        }

        return count;
    }
}
