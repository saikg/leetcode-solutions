package io.github.saikg.leetcode.s33;

public class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int pivot = pivotLocation(nums);
        if (pivot != 0) {
            int left = binarySearch(nums, 0, pivot, target);
            int right = binarySearch(nums, pivot + 1, n - 1, target);
            if (right != -1) return left;
            return right;
        }
        return pivot;
    }

    private int pivotLocation(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] <= target) {

            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 7, 9, 12, 16, 19, 22, 1};
        int target = 12;

        Solution solution = new Solution();
        int ans = solution.search(nums, target);
        System.out.println("ans = " + ans);
    }
}
