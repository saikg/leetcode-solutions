package io.github.saikg.leetcode.s384;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {

    int[] original;
    Random random = new Random();

    public Solution(int[] nums) {
        original = nums;
    }

    public int[] reset() {
        return original;
    }

    public int[] shuffle() {
        int n = original.length;
        int[] result = Arrays.copyOf(original, n);
        for (int i = 0; i < original.length; i++) {
            int b = randomIndex(0, n);
            swap(result, i, b);
        }
        return result;
    }

    private int randomIndex(int min, int max) {
        return min + random.nextInt(max - min);
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}