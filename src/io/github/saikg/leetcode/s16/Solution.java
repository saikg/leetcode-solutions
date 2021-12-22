package io.github.saikg.leetcode.s16;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length, closest = Integer.MAX_VALUE, minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                int diff = Math.abs(sum - target);
                if (diff < minDiff) {
                    minDiff = diff;
                    closest = sum;
                }

                if (diff == 0) {
                    return target;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closest;
    }
}