package io.github.saikg.leetcode.s398;

import java.util.*;

public class Solution {

    private static final Random indexPicker = new Random();

    Map<Integer, List<Integer>> index;

    public Solution(int[] nums) {
        index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index
                    .computeIfAbsent(nums[i], v -> new ArrayList<>())
                    .add(i);
        }
    }

    public int pick(int target) {
        List<Integer> possibleIndices = index.get(target);
        return reservoirSampling(possibleIndices);
    }

    private int reservoirSampling(List<Integer> indices) {
        int value = indices.get(0);
        for (int i = 1; i < indices.size(); i++) {
            int r = indexPicker.nextInt(i + 1);
            if (r == 0) {
                value = indices.get(i);
            }
        }
        return value;
    }
}