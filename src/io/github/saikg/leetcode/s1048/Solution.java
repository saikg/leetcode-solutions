package io.github.saikg.leetcode.s1048;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int longestStrChain(String[] words) {
        int n = words.length;
        int smallestWordLength = Integer.MAX_VALUE;
        Map<String, Integer> longestChainWithString = new HashMap<>();
        SortedMap<Integer, List<String>> wordsByLength = new TreeMap<>();
        for (String word: words) {
            wordsByLength
                    .computeIfAbsent(word.length(), v -> new ArrayList<>())
                    .add(word);
            smallestWordLength = Math.min(word.length(), smallestWordLength);
            longestChainWithString.put(word, 1);
        }

        int longestChain = 1;
        for (int size: wordsByLength.keySet()) {
            if (!wordsByLength.containsKey(size + 1)) {
                continue;
            }
            for (String pred: wordsByLength.get(size)) {
                for (String succ: wordsByLength.get(size + 1)) {
                    if (!predecessor(pred, succ)) {
                        continue;
                    }
                    int p = longestChainWithString.get(pred);
                    int s = longestChainWithString.get(succ);
                    s = Math.max(s, p + 1);
                    longestChainWithString.put(succ, s);
                    longestChain = Math.max(longestChain, s);
                }
            }
        }
        return longestChain;
    }

    private boolean predecessor(String before, String after) {
        if (after.length() - 1 != before.length()) {
            return false;
        }

        int mismatches = 0;
        int i = 0, j = 0;
        while (i < before.length() && j < after.length()) {
            if (before.charAt(i) == after.charAt(j)) {
                i++;
            } else {
                mismatches++;
            }
            j++;
        }
        return (i == before.length()) && (mismatches <= 1);
    }
}