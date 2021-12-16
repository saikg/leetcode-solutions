package io.github.saikg.leetcode.s448;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int idx = convertToIndex(nums[i]);
            if (idx >= 0 && idx < n) {
                nums[idx] = -Math.abs(nums[idx]);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    private int convertToIndex(int x) {
        return Math.abs(x) - 1;
    }
}
