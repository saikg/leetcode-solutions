package io.github.saikg.leetcode.s1707;

import java.util.*;

public class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {

        Arrays.sort(nums);

        int ql = queries.length;
        Integer[] qIdx = new Integer[ql];
        for (int i = 0; i < ql; i++) {
            qIdx[i] = i;
        }
        Arrays.sort(qIdx, Comparator.comparingInt(a -> queries[a][1]));

        Trie trie = new Trie();

        int numIdx = 0;
        int[] results = new int[ql];
        for (int i = 0; i < ql; i++) {
            int qi = qIdx[i];
            int[] query = queries[qi];
            int x = query[0], m = query[1];
            while (numIdx < nums.length && nums[numIdx] <= m) {
                trie.add(nums[numIdx++]);
            }
            results[qi] = trie.find(x);
        }
        return results;
    }

    static class Trie {

        BinaryNode root;

        Trie() {
            root = new BinaryNode();
        }

        void add(int x) {
            BinaryNode node = root;
            for (int p = 31; p >= 0; p--) {
                int bit = (x >> p) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new BinaryNode();
                }
                node = node.children[bit];
            }
        }

        int find(int x) {
            BinaryNode node = root;
            if (node.hasNoChild()) {
                return -1;
            }

            int xor = 0;
            for (int p = 31; p >= 0; p--) {
                int bit = (x >> p) & 1;
                int toggleBit = 1 - bit;

                xor = xor << 1;
                if (node.children[toggleBit] != null) {
                    xor = xor | 1;
                    node = node.children[toggleBit];
                } else {
                    node = node.children[bit];
                }
            }
            return xor;
        }

        static class BinaryNode {

            BinaryNode[] children;

            BinaryNode() {
                children = new BinaryNode[2];
            }

            boolean hasNoChild() {
                return children[0] == null && children[1] == null;
            }
        }
    }
}