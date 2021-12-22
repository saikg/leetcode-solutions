package io.github.saikg.leetcode.s259;

import java.util.Arrays;

public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length, triples = 0;
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    triples += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triples;
    }
}