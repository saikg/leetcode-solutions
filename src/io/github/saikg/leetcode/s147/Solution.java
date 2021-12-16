package io.github.saikg.leetcode.s147;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

import java.util.Arrays;

public class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode preHead = new ListNode();
        preHead.next = head;
        return preHead.next;
    }

    void insertionSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i-1] <= nums[i]) {
                continue;
            }

            int val = nums[i], k = i;
            while (k > 0 && nums[k-1] > val) {
                nums[k] = nums[k-1];
                k--;
            }
            nums[k] = val;
            System.out.println(Arrays.toString(nums));
        }
    }

    public static void main(String[] args) {
        int[] nums = {9,3,2,8,1,5,4,7,6};
        Solution solution = new Solution();
        solution.insertionSort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));
    }
}