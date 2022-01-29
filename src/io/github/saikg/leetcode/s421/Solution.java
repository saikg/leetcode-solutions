package io.github.saikg.leetcode.s421;

import java.util.*;

public class Solution {
    public int findMaximumXOR(int[] nums) {
//        return bruteForceImpl(nums);
//        return prefixHashSetImpl(nums);
        return binaryTrieImpl(nums);
    }

    private int bruteForceImpl(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                max = Math.max(nums[i] ^ nums[j], max);
            }
        }
        return max;
    }

    private int prefixHashSetImpl(int[] nums) {
        int maxNum = Arrays.stream(nums).max().getAsInt();
        int L = Integer.toBinaryString(maxNum).length();

        Set<Integer> prefixes = new HashSet<>();
        int maxXor = 0, currXor = 0;
        for (int i = L-1; i >= 0; i--) {
            maxXor = maxXor << 1;
            currXor = maxXor | 1;

            prefixes.clear();
            for (int num : nums) {
                prefixes.add(num >> i);
            }

            for (int p : prefixes) {
                if (prefixes.contains(currXor ^ p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }

    private int binaryTrieImpl(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int len = Integer.toBinaryString(max).length();

        Trie trie = new Trie();
        for (int num : nums) {
            StringBuilder paddedBinary = new StringBuilder();
            String binary = Integer.toBinaryString(num);

            int currLen = binary.length();
            for (int i = 0; i < len - currLen; i++) {
                paddedBinary.append('0');
            }
            paddedBinary.append(binary);

            trie.add(paddedBinary.toString());
        }

        int maxXor = 0;
        for (int num : nums) {
            int xor = 0;

            Trie.BinaryNode node = trie.root;
            String binary = Integer.toBinaryString(num);
            for (char ch : binary.toCharArray()) {
                int bit = ch - '0';
                int toggleBit = 1 - bit;

                xor = xor << 1;
                if (node.children[toggleBit] != null) {
                    xor = xor | 1;
                    node = node.children[toggleBit];
                } else {
                    node = node.children[bit];
                }
            }

            maxXor = Math.max(xor, maxXor);
        }

        return maxXor;
    }

    static class Trie {

        BinaryNode root;

        Trie() {
            root = new BinaryNode();
        }

        void add(String binary) {
            BinaryNode curr = root;
            for (char ch : binary.toCharArray()) {
                int m = ch - '0';
                if (curr.children[m] == null) {
                    curr.children[m] = new BinaryNode();
                }
                curr = curr.children[m];
            }
            curr.isTerminal = true;
        }

        static class BinaryNode {
            boolean isTerminal;
            BinaryNode[] children;

            BinaryNode() {
                isTerminal = false;
                children = new BinaryNode[2];
            }

            boolean hasNoChild() {
                return children[0] == null && children[1] == null;
            }
        }
    }
}