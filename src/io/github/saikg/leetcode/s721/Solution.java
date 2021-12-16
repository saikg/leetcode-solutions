package io.github.saikg.leetcode.s721;

import java.util.*;

public class Solution {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> disjointSets = new HashMap<>();
        for (int j = 0; j < accounts.size(); j++) {
            List<String> entry = accounts.get(j);
            Integer root = j;
            for (int i = 1; i < entry.size(); i++) {
                if (disjointSets.containsKey(entry.get(i))) {
                    root = disjointSets.get(entry.get(i));
                }
            }
            for (int i = 1; i < entry.size(); i++) {
                disjointSets.put(entry.get(i), root);
            }
        }

        Map<Integer, List<String>> mergedAccountsByRoot = new HashMap<>();
        for (String address: disjointSets.keySet()) {
            int root = disjointSets.get(address);
            mergedAccountsByRoot
                    .computeIfAbsent(root, v -> new ArrayList<>())
                    .add(address);
        }
        for (int root: mergedAccountsByRoot.keySet()) {
            mergedAccountsByRoot.get(root).sort(String::compareTo);
            mergedAccountsByRoot.get(root).add(0, accounts.get(root).get(0));
        }
        return new ArrayList<>(mergedAccountsByRoot.values());
    }
}

