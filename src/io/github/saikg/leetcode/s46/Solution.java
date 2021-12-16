package io.github.saikg.leetcode.s46;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> permutations = new ArrayList<>();
        permutations.add(createList(nums));
        while (hasNext(nums)) {
            nextPermutation(nums);
            permutations.add(createList(nums));
        }
        return permutations;
    }

    private boolean hasNext(int[] nums) {
        int n = nums.length;
        for (int i = n-1; i > 0; i--) {
            if (nums[i-1] < nums[i]) {
                return true;
            }
        }
        return false;
    }

    private void nextPermutation(int[] nums) {
        int n = nums.length, f = n-1;
        for (int i = n-1; i > 0; i--) {
            if (nums[i-1] < nums[i]) {
                f = i-1;
                break;
            }
        }
        int pivot = n-1;
        while(nums[f] > nums[pivot]) {
            pivot--;
        }
        swap(nums, pivot, f);
        reverse(nums, f+1, n-1);
    }

    private void swap(int[] nums, int p, int q) {
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }

    private void reverse(int[] nums, int p, int q) {
        while (p < q) {
            swap(nums, p++, q--);
        }
    }

    private List<Integer> createList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int h: nums) {
            list.add(h);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        Solution solution = new Solution();
        for (List<Integer> permutation: solution.permute(nums)) {
            System.out.println(permutation);
        }
    }
}

