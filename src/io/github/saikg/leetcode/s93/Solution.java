package io.github.saikg.leetcode.s93;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    List<String> possibleIps;

    public List<String> restoreIpAddresses(String s) {
        possibleIps = new ArrayList<>();
        backtrack(0, s, new LinkedList<>());
        return possibleIps;
    }

    private void backtrack(int idx, String s, LinkedList<Integer> subnets) {
        int remainingSubnets = 4 - subnets.size();
        int remainingChars = s.length() - idx;
        if (remainingChars > 3 * remainingSubnets) {
            return;
        }

        if (subnets.size() == 4 && idx == s.length()) {
            String ip = convertToString(subnets);
            if (ip.length() - 3 == s.length())
                possibleIps.add(convertToString(subnets));
            return;
        }

        if (idx == s.length()) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int subnet = 0;
        for (int i = idx; i < s.length(); i++) {
            subnet *= 10;
            subnet += s.charAt(i) - '0';
            if (subnet > 255) {
                break;
            }

            subnets.add(subnet);
            backtrack(i + 1, s, subnets);
            subnets.removeLast();
        }
    }

    private String convertToString(List<Integer> subnets) {
        return subnets.stream()
                .map(v -> Integer.toString(v))
                .reduce((a,b) ->  a + "." + b)
                .get();
    }
}