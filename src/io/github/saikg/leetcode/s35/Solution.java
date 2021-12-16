package io.github.saikg.leetcode.s35;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 0;

        Solution solution = new Solution();
        int ans = solution.searchInsert(nums, target);
        System.out.println("ans = " + ans);
    }
}
