package io.github.saikg.leetcode.s47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> permutations = new ArrayList<>();

        int count = 1;
        permutations.add(convertToList(nums));
        while (hasNext(nums)) {
            next(nums);
            List<Integer> permutation = convertToList(nums);
            if (!permutations.get(count - 1).equals(permutation)) {
                permutations.add(convertToList(nums));
                count++;
            }
        }
        return permutations;
    }

    private List<Integer> convertToList(int[] nums) {
        List<Integer> permutation = new ArrayList<>();
        for (int num : nums) {
            permutation.add(num);
        }
        return permutation;
    }

    private void next(int[] nums) {
        int n = nums.length;

        // find point of inflection
        int max = nums[n-1], inflectionPoint = n-1;
        for (int i = n-2; i >= 0; i--) {
            if (max > nums[i]) {
                inflectionPoint = i;
                break;
            }
            max = nums[i];
        }

        // find next highest number in (inflection, n-1)
        int nextHighest = inflectionPoint;
        for (int i = n-1; i > inflectionPoint; i--) {
            if (nums[i] > nums[inflectionPoint]) {
                nextHighest = i;
                break;
            }
        }

        // swap inflection point with next highest
        swap(nums, inflectionPoint, nextHighest);

        // reverse sequence from (inflectionPoint, n-1]
        reverse(nums, inflectionPoint+1, n-1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private void swap(int[] nums, int p, int q) {
        int t = nums[p];
        nums[p] = nums[q];
        nums[q] = t;
    }

    // check if sequence is decreasing
    private boolean hasNext(int[] nums) {
        int n = nums.length;
        int seenMax = nums[n-1];
        for (int i = n-1; i >= 0; i--) {
            if (nums[i] < seenMax) {
                return true;
            }
            seenMax = nums[i];
        }
        return false;
    }
}
