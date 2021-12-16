package io.github.saikg.leetcode.s78;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<Integer>> output;

    public List<List<Integer>> subsets(int[] nums) {
        output = new ArrayList<>();
        generateLexicographicSequences(nums);
        return output;
    }

    private void generateLexicographicSequences(int[] nums) {
        int n = nums.length;
        int l = (int) Math.pow(2, n), u = l << 1;

        for (int i = l; i < u; i++) {
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> seq = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (bitmask.charAt(j) == '1') {
                    seq.add(nums[j]);
                }
            }
            output.add(seq);
        }
    }
}