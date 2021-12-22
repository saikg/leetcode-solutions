package io.github.saikg.leetcode.s167;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int fptr = 0, bptr = n-1;
        while (fptr < bptr) {
            int v = numbers[fptr] + numbers[bptr];
            if (v == target) {
                return new int[]{fptr, bptr};
            }

            int diff = target - v;
            if (numbers[fptr + 1] - numbers[fptr] > diff) {
                bptr--;
            } else {
                fptr++;
            }
        }
        return null;
    }
}