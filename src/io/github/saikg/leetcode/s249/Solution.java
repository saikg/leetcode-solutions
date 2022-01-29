package io.github.saikg.leetcode.s249;

import java.util.*;

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<Integer, List<String>> f = new HashMap<>();
        for (String s : strings) {
            int code = hash(s);
            f.computeIfAbsent(code, a -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(f.values());
    }

    private int hash(String s) {
        long code = 0;
        for (int i = 0; i < s.length()-1; i++) {
            int diff = (s.charAt(i+1) - s.charAt(i)) % 26;
            code *= 26;
            code += diff;
            code %= 1000000007;
        }
        return (int)code;
    }
}