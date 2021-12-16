package io.github.saikg.leetcode.s139;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    int[] dp;

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return wordBreak(s, 0, wordSet);
    }

    private boolean wordBreak(String s, int id, Set<String> wordSet) {
        if (id == s.length()) {
            return true;
        }

        if (dp[id] != -1) {
            return (dp[id] == 1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean ans = false;
        for (int i = id; i < s.length() && !ans; i++) {
            stringBuilder.append(s.charAt(i));
            if (wordSet.contains(stringBuilder.toString())) {
                ans = wordBreak(s, i + 1, wordSet);
            }
        }
        dp[id] = ans ? 1 : 0;
        return ans;
    }
}