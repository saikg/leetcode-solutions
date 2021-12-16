package io.github.saikg.leetcode.s39;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<List<Integer>> allCombinations;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        allCombinations = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>());
        return allCombinations;
    }

    private void backtrack(int[] candidates, int idx, int target, List<Integer> combination) {
        if (target < 0 || idx == candidates.length) {
            return;
        }

        if (target == 0) {
            allCombinations.add(new ArrayList<>(combination));
            return;
        }

        for (int j = idx; j < candidates.length; j++) {
            combination.add(candidates[j]);
            backtrack(candidates, j, target - candidates[j], combination);
            combination.remove(combination.size() - 1);
        }
    }
}