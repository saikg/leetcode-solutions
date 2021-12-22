package io.github.saikg.leetcode.s1255;

import java.util.Arrays;

public class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] count = new int[26];
        for (char ch: letters) {
            count[ch - 'a']++;
        }

        return maxScoreWords(0, words, count, score);
    }

    private int maxScoreWords(int idx, String[] words, int[] count, int[] score) {
        if (idx == words.length) {
            return 0;
        }

        boolean usable = true;
        int wordScore = 0;
        int[] copy = Arrays.copyOf(count, count.length);
        for (char ch: words[idx].toCharArray()) {
            copy[ch - 'a']--;
            if (copy[ch - 'a'] < 0) {
                usable = false;
                break;
            }
            wordScore += score[ch - 'a'];
        }

        int skip = maxScoreWords(idx + 1, words, count, score);
        if (!usable) {
            return skip;
        }
        int use = wordScore + maxScoreWords(idx + 1, words, copy, score);
        return Math.max(use, skip);
    }
}