package io.github.saikg.leetcode.s34;

import java.util.Arrays;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
//        return linearSearch(nums, target);
        return binarySearch(nums, target);
    }

    // O(n) solution
    private int[] linearSearch(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int p = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (target == nums[i]) {
                if (p == 0){
                    ans[p++] = i;
                    ans[p] = i;
                } else {
                    ans[p] = Math.max(ans[p], i);
                }
            }
        }
        return ans;
    }

    // O(log n) binary search
    private int[] binarySearch(int[] nums, int target) {
        return new int[]{
                lowerBound(nums, target),
                upperBound(nums, target)
        };
    }

    // O(log n) binary search lower bound
    private int lowerBound(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int low = 0, high = n-1;
        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println("lower bound: [" + low + "," + high + "]");
            if (target <= nums[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low] == target ? low : -1;
    }

    // O(log n) binary search upper bound
    private int upperBound(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int low = 0, high = n-1;
        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println("upper bound: [" + low + "," + high + "]");
            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        System.out.println("upper bound: [" + low + "," + high + "]");
        return nums[high] == target ? high : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{
                5,7,7,8,8,10
        };
        int target = 8;

        Solution solution = new Solution();
        int[] ans = solution.searchRange(nums, target);
        System.out.println("Arrays.toString(ans) = " + Arrays.toString(ans));

    }
}
