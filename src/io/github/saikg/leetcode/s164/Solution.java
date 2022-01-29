package io.github.saikg.leetcode.s164;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int maximumGap(int[] nums) {
        return bucketSort(nums);
    }

    private int bucketSort(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        int n = nums.length;
        int bucketSize = (max - min + 1) / n;
        if (bucketSize * n < (max - min + 1)) {
            bucketSize++;
        }

        List<List<Integer>> buckets = new ArrayList<>();
        List<Integer> bucketMin = new ArrayList<>();
        List<Integer> bucketMax = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
            bucketMin.add(Integer.MAX_VALUE);
            bucketMax.add(Integer.MIN_VALUE);
        }

        for (int num : nums) {
            int bucketIdx = (num - min) / bucketSize;
            buckets.get(bucketIdx).add(num);
            bucketMin.set(bucketIdx, Math.min(bucketMin.get(bucketIdx), num));
            bucketMax.set(bucketIdx, Math.max(bucketMax.get(bucketIdx), num));
        }

        for (int i = 0; i < n; i++) {

        }

        return 0;
    }
}