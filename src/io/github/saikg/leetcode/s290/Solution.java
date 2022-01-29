package io.github.saikg.leetcode.s290;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        Map<Character, String> function = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];
            if (!function.computeIfAbsent(ch, v -> word).equals(word)) {
                return false;
            }
        }

        return (new HashSet<>(function.values())).size() == function.keySet().size();
    }
}