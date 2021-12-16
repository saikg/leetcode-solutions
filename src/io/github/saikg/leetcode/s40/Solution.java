package io.github.saikg.leetcode.s40;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private List<List<Integer>> uniqueCombinations;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int candidate: candidates) {
            counter.put(candidate, counter.getOrDefault(candidate, 0) + 1);
        }
        List<Integer> keys = counter.keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList());

        uniqueCombinations = new ArrayList<>();
        backtrack(counter, keys, 0, target, new ArrayList<>());
        return uniqueCombinations;
    }

    private void backtrack(Map<Integer, Integer> counter,
                           List<Integer> distinctNums,
                           int idx, int target,
                           List<Integer> combination) {

        if (target == 0) {
            uniqueCombinations.add(new ArrayList<>(combination));
            return;
        }
        if (target < 0 || idx == distinctNums.size()) {
            return;
        }

        for (int j = idx; j < distinctNums.size(); j++) {
            int num = distinctNums.get(j);
            int freq = counter.get(num);

            counter.put(num, freq - 1);
            combination.add(num);
            if (freq > 1)
                backtrack(counter, distinctNums, j, target - num, combination);
            else
                backtrack(counter, distinctNums, j + 1, target - num, combination);
            combination.remove(combination.size() - 1);
            counter.put(num, freq);
        }
    }
}