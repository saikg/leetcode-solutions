package io.github.saikg.leetcode.s15;

import java.util.*;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        Set<Integer> numsSet = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;
        int i = 0;
        while (i < n) {
            int q = nums[i], j = i + 1;
            while (j < n) {
                int p = nums[j];
                int v = -(p + nums[i]);
                if (numsSet.contains(v)) {
                    result.add(Arrays.asList(v, nums[i], p));
                }

                while (j < n && nums[j] == p) {
                    j++;
                }
            }

            while (i < n && nums[i] == q) {
                i++;
            }
            numsSet.add(q);
        }
        return result;
    }
}