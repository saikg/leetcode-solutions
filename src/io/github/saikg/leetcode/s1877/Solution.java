package io.github.saikg.leetcode.s1877;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return IntStream.range(0, n / 2)
                .map(v -> nums[v] + nums[n-v])
                .min().getAsInt();
    }
}