package io.github.saikg.leetcode.s1525;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Solution {
    public int numSplits(String s) {
        int n = s.length();

        int[][] intervals = new int[26][2];
        for (int i = 0; i < 26; i++) {
            intervals[i][0] = -1;
            intervals[i][1] = -1;
        }

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            int idx = ch - 'a';
            if (intervals[idx][0] == -1) {
                intervals[idx][0] = i;
            }
            intervals[idx][1] = i;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int numLeft = 0;
            int numRight = 0;
            if (numLeft == numRight) {
                res++;
            }
        }
        return res;
    }

    private int countCharacters(int idx, int[][] intervals,
            BiFunction<Integer, Integer, Boolean> predicate) {

        int count = 0;
        for (int f = 0; f < 26; f++) {
            if (intervals[f][0] != -1 && predicate.apply(f, idx)) {
                count++;
            }
        }
        return count;
    }
}