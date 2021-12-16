package io.github.saikg.leetcode.s373;

import java.util.*;

public class Solution {

    class Pair {
        int[] arr;
        int sum, index;
        Pair(int a, int b, int index) {
            arr = new int[]{a, b};
            sum = a + b;
            this.index = index;
        }
    }

    class PairComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return Integer.compare(a.sum, b.sum);
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<Pair> queue = new PriorityQueue<>(k, new PairComparator());
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m && i < k; i++) {
            queue.offer(new Pair(nums1[i], nums2[0], 0));
        }
        for (int i = 1; i <= k && !queue.isEmpty(); i++) {
            Pair pair = queue.poll();
            result.add(Arrays.asList(pair.arr[0], pair.arr[1]));
            if (pair.index < nums2.length - 1) {
                int next = pair.index + 1;
                queue.offer(new Pair(pair.arr[0], nums2[next], next));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,7,11};
        int[] nums2 = new int[]{2,4,6};
        int k = 9;

        Solution solution = new Solution();
        System.out.println(solution.kSmallestPairs(nums1, nums2, k));
    }
}
