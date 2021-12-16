package io.github.saikg.leetcode.s540;

public class Solution {

    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int p: nums) ans ^= p;
        return ans;
    }

    private int binarySearch(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            boolean isMidEven = (mid & 1) == 0;
            if (isMidEven) {
                if (nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                if (nums[mid] == nums[mid + 1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return right;
    }
}
