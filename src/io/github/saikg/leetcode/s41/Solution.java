package io.github.saikg.leetcode.s41;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        for (int num: nums) {
            num = Math.abs(num) - 1;
            if (num == n) {
                continue;
            }
            setNegativeSign(nums, num);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void setNegativeSign(int[] arr, int id) {
        arr[id] = -Math.abs(arr[id]);
    }
}