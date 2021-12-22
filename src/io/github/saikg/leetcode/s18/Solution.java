package io.github.saikg.leetcode.s18;

import java.util.*;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
        if (k == 2) {
            return twoSum(nums, target, start);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1])
                continue;
            List<List<Integer>> intermediateResult = kSum(nums, target-nums[i], k-1, i+1);
            for (List<Integer> combination: intermediateResult) {
                List<Integer> updatedCombination = new ArrayList<>();
                updatedCombination.add(nums[i]);
                updatedCombination.addAll(combination);
                result.add(updatedCombination);
            }
        }
        return result;
    }

    private List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        int left = start, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                result.add(Arrays.asList(nums[left++], nums[right--]));
                while (left < right && nums[left] == nums[left-1]) {
                    left++;
                }
            }
        }
        return result;
    }
}