package io.github.saikg.leetcode.s131;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<String>> partitions;

    public List<List<String>> partition(String s) {
        partitions  = new ArrayList<>();
        solve(s, 0, new ArrayList<>());
        return partitions;
    }

    private void solve(String s, int j, List<String> curr) {
        if (j == s.length()) {
            partitions.add(curr);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = j; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (isPalindrome(sb)) {
                List<String> updated = new ArrayList<>(curr);
                updated.add(sb.toString());
                solve(s, i+1, updated);
            }
        }
    }

    private boolean isPalindrome(StringBuilder sb) {
        String s = sb.toString();
        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }
}