package io.github.saikg.leetcode.s792;

import java.util.*;

public class Solution {
    Map<Character, List<Integer>> map = new HashMap<>();

    public int numMatchingSubseq(String s, String[] words) {
        preprocess(s);

        int ans = 0;
        for (String word: words) {
            ans += isSubsequence(s, word) ? 1 : 0;
        }
        return ans;
    }

    private void preprocess(String s) {
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), c -> new ArrayList<>()).add(i);
        }
    }

    private boolean isSubsequence(String s, String word) {
        int m = word.length();
        if (m > s.length()) {
            return false;
        }

        Map<Character, Iterator<Integer>> iterators = new HashMap<>();
        for (char key: map.keySet()) {
            iterators.put(key, map.get(key).iterator());
        }

        int ptr = -1;
        for (int i = 0; i < m; i++) {
            char ch = word.charAt(i);
            if (!iterators.containsKey(ch) || !iterators.get(ch).hasNext()) {
                return false;
            }

            Iterator<Integer> it = iterators.get(ch);
            boolean found = false;
            while (it.hasNext()) {
                int next = it.next();
                if (next > ptr) {
                    ptr = next;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }
        return true;
    }
}
