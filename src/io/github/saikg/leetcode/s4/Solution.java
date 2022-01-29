package io.github.saikg.leetcode.s4;

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // to achieve O(log(min(m, n))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n = nums1.length;
        int m = nums2.length;

        int iLow = 0, iHigh = nums1.length;
        while (iLow < iHigh) {
            int i = (iLow + iHigh) / 2;
            int j = (m + n + 1) / 2 - i;

        }
        return 1.0;
    }

    // linear merge algorithm
    private double bruteForce(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merged = new int[m + n];
        int p = 0, i = 0, j = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                merged[p++] = nums1[i++];
            } else {
                merged[p++] = nums2[j++];
            }
        }
        while (i < m) {
            merged[p++] = nums1[i++];
        }
        while (j < n) {
            merged[p++] = nums2[j++];
        }
        if ((m + n) % 2 == 1) {
            return (double)merged[(m + n) / 2];
        }
        return (merged[(m + n) / 2] + merged[(m + n) / 2 - 1]) / 2.0;
    }
}