package io.github.saikg.leetcode.s382;

import io.github.saikg.leetcode.common.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {

    Sampler sampler;

    public Solution(ListNode head) {
        sampler = NewSampler(FixedSizeSamplingImpl.NAME, head);
    }

    public int getRandom() {
        return sampler.getRandom();
    }

    interface Sampler {
        int getRandom();
    }

    private Sampler NewSampler(String samplerName, ListNode head) {
        switch (samplerName) {
            case FixedSizeSamplingImpl.NAME:
                return new FixedSizeSamplingImpl(head);
            case ReservoirSamplingImpl.NAME:
                return new ReservoirSamplingImpl(head);
            case LinearScanImpl.NAME:
                return new LinearScanImpl(head);
        }
        return new FixedSizeSamplingImpl(head);
    }

    /*
    * Fixed size sampling implementation
    * Space complexity: O(N)
    * Time complexity: O(N) - init; O(1) - getRandom
    * */
    static class FixedSizeSamplingImpl implements Sampler {

        private static final String NAME = "FixedSizeSampling";
        private static final Random randomIndexGenerator = new Random();

        List<Integer> randomAccessList = new ArrayList<>();

        FixedSizeSamplingImpl(ListNode head) {
            while (head != null) {
                randomAccessList.add(head.val);
                head = head.next;
            }
        }

        public int getRandom() {
            int max = randomAccessList.size();
            int val = randomIndexGenerator.nextInt(max);
            return randomAccessList.get(val);
        }
    }

    /*
    * Reservoir sampling using Algorithm R
    * Space complexity: O(1)
    * Time complexity: O(1) - init; O(N) - query
    * */
    static class ReservoirSamplingImpl implements Sampler {

        private static final String NAME = "ReservoirSampling";
        private static final Random randomIntegerGenerator = new Random();

        ListNode head;

        public ReservoirSamplingImpl(ListNode node) {
            head = node;
        }

        public int getRandom() {
            int chosen = head.val, nodeIdx = 1;

            ListNode curr = head.next;
            while (curr != null) {
                int random = randomIntegerGenerator.nextInt(nodeIdx + 1);
                if (random == 0) {
                    chosen = curr.val;
                }
                curr = curr.next;
                nodeIdx++;
            }

            return chosen;
        }
    }

    /*
    * Linear scan to reach random index with precomputed size
    * Space complexity: O(1)
    * Time complexity: O(N) - init; O(N) - query
    * */
    static class LinearScanImpl implements Sampler {

        private static final String NAME = "LinearScan";
        private static final Random randomIndexGenerator = new Random();

        ListNode head;
        int size;

        LinearScanImpl(ListNode node) {
            head = node;
            size = 0;
            ListNode curr = head;
            while (curr != null) {
                curr = curr.next;
                size++;
            }
        }

        public int getRandom() {
            int idx = randomIndexGenerator.nextInt(size);
            ListNode curr = head;
            for (int i = 0; i < idx; i++) {
                curr = curr.next;
            }
            return curr.val;
        }
    }
}