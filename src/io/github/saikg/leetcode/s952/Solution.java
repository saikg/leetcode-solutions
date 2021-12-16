package io.github.saikg.leetcode.s952;

import java.util.*;

public class Solution {

    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();

        DisjointSets disjointSets = new DisjointSets(max);
        Map<Integer, Integer> numFactorMap = new HashMap<>();
        for (int num: nums) {
            List<Integer> factors = primeDecompose(num);
            if (factors.isEmpty())
                continue;
            numFactorMap.put(num, factors.get(0));
            for (int i = 0; i < factors.size() - 1; i++) {
                disjointSets.union(factors.get(i), factors.get(i+1));
            }
        }

        int ans = 0;
        Map<Integer, Integer> groupCount = new HashMap<>();
        for (int num: nums) {
            int root = disjointSets.root(numFactorMap.get(num));
            groupCount.put(root, groupCount.getOrDefault(root, 0) + 1);
            ans = Math.max(ans, groupCount.get(root));
        }
        return ans;
    }

    private List<Integer> primeDecompose(int c) {
        Set<Integer> primes = new HashSet<>();
        int factor = 2;
        while (factor * factor <= c) {
            if (c % factor == 0) {
                primes.add(factor);
                c /= factor;
            } else {
                factor++;
            }
        }
        primes.add(c);
        return new ArrayList<>(primes);
    }

    class DisjointSets {

        int[] parent, size;

        DisjointSets(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        boolean connected(int a, int b) {
            return root(a) == root(b);
        }

        int root(int a) {
            while (a != parent[a]) {
                parent[a] = parent[parent[a]];
                a = parent[a];
            }
            return a;
        }

        void union(int a, int b) {
            int rootA = root(a);
            int rootB = root(b);
            if (rootA == rootB) {
                return;
            }

            if (size[rootA] >= size[rootB]) {
                parent[rootB] = parent[rootA];
                size[rootA] += size[rootB];
            } else {
                parent[rootA] = parent[rootB];
                size[rootB] += size[rootA];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        Solution solution = new Solution();
        int ans = solution.largestComponentSize(nums);
        System.out.println("ans = " + ans);
    }
}