package io.github.saikg.leetcode.s31;

public class Solution {

    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // find point where increasing sequence ends from last
        int pivot = n-1;
        while (pivot > 0 && nums[pivot-1] >= nums[pivot]) {
            pivot--;
        }
        pivot--;

        // replace the pivot with the next highest number in the sequence
        if (pivot >= 0) {
            int nextHighest = n-1;
            while (nums[pivot] >= nums[nextHighest]) {
                nextHighest--;
            }
            swap(nums, pivot, nextHighest);
        }

        // reverse the sequence after pivot
        reverse(nums, pivot + 1, n - 1);
    }

    private void swap(int[] nums, int p, int q) {
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
