package io.github.saikg.leetcode.s77;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private List<List<Integer>> allCombinations;

    public List<List<Integer>> combine(int n, int k) {
        allCombinations = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            backtrack(i, n, k, new ArrayList<>());
        }
        return allCombinations;
    }

    private void backtrack(int f, int n, int k, List<Integer> combination) {
        if (n - f + 1 + combination.size() < k) {
            return;
        }

        combination.add(f);
        if (combination.size() == k) {
            allCombinations.add(new ArrayList<>(combination));
        } else {
            for (int i = f + 1; i <= n; i++) {
                backtrack(i, n, k, combination);
            }
        }
        combination.remove(combination.size() - 1);
    }
}