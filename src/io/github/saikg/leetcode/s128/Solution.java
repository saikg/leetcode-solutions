package io.github.saikg.leetcode.s128;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num: nums) {
            numSet.add(num);
        }

        int ans = 0;
        for (int num: nums) {
            if (numSet.contains(num - 1)) {
                continue;
            }

            int nextKey = num + 1, seqLen = 1;
            while (numSet.contains(nextKey)) {
                seqLen++;
                nextKey++;
            }
            ans = Math.max(seqLen, ans);
        }
        return ans;
    }
}
