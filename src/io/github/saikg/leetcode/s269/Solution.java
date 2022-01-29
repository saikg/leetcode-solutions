package io.github.saikg.leetcode.s269;

import java.util.*;

public class Solution {
    public String alienOrder(String[] words) {
        int n = words.length;
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inorder = new HashMap<>();

        for (String word : words) {
            for (Character ch : word.toCharArray()) {
                graph.computeIfAbsent(ch, v -> new ArrayList<>());
                inorder.computeIfAbsent(ch, v -> 0);
            }
        }

        for (int i = 0; i < n - 1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            int l1 = word1.length(), l2 = word2.length();
            int l3 = Math.min(l1, l2);
            for (int j = 0; j < l3; j++) {
                char char1 = word1.charAt(j);
                char char2 = word2.charAt(j);
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }

                if (char1 != char2) {
                    graph.get(char1).add(char2);
                    inorder.put(char2, inorder.get(char2) + 1);
                    break;
                }
            }
        }

        Queue<Character> zeroInorderNodes = new LinkedList<>();
        for (char ch : inorder.keySet()) {
            if (inorder.get(ch) == 0) {
                zeroInorderNodes.add(ch);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!zeroInorderNodes.isEmpty()) {
            char ch = zeroInorderNodes.poll();
            stringBuilder.append(ch);
            for (Character next : graph.get(ch)) {
                inorder.put(next, inorder.get(next) - 1);
                if (inorder.get(next) == 0) {
                    zeroInorderNodes.add(next);
                }
            }
        }
        if (stringBuilder.length() < inorder.size()) {
            return "";
        }
        return stringBuilder.toString();
    }
}