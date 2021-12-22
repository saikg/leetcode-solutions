package io.github.saikg.leetcode.s1255;

import java.util.Arrays;

public class Solution2 {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] count = new int[26];
        for (char ch: letters) {
            count[ch - 'a']++;
        }

        int n = words.length, maxScore = 0;
        for (int bitmask = 0; bitmask < 1 << n; bitmask++) {
            maxScore = Math.max(computeScore(bitmask, words, count, score), maxScore);
        }
        return maxScore;
    }

    private int computeScore(int bitmask, String[] words, int[] count, int[] score) {
        int p = 1, currScore = 0, n = words.length;
        int[] copy = Arrays.copyOf(count, count.length);
        for (int i = 0; i < n; i++, p = p << 1) {
            if ((bitmask & p) == 0) {
                continue;
            }
            for (char ch: words[n-1-i].toCharArray()) {
                int offset = ch-'a';
                copy[offset]--;
                if (copy[offset] < 0) {
                    return 0;
                }
                currScore += score[offset];
            }
        }
        return currScore;
    }
}
