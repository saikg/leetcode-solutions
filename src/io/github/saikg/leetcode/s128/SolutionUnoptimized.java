package io.github.saikg.leetcode.s128;

import java.util.HashMap;

public class SolutionUnoptimized {
    class UnionFind {
        HashMap<Integer, Integer> parents;
        HashMap<Integer, Integer> size;

        UnionFind() {
            parents = new HashMap<>();
            size = new HashMap<>();
        }

        void setParent(int child, int parent) {
            parents.put(child, parents.get(parent));
            size.put(parent, size.get(parent) + size.get(child));
        }

        public void addNumber(int num) {
            if (parents.containsKey(num)) {
                return;
            }

            parents.put(num, num);
            size.put(num, 1);
        }

        public void union(int a, int b) {
            int rootA = root(a);
            int rootB = root(b);
            if (rootA == rootB) {
                return;
            }

            if (size.get(rootA) >= size.get(rootB)) {
                setParent(rootB, rootA);
            } else {
                setParent(rootA, rootB);
            }
        }

        public int root(int a) {
            while (a != parents.get(a)) {
                int grandParent = parents.get(a);
                grandParent = parents.get(grandParent);

                parents.put(a, grandParent);
                a = grandParent;
            }
            return a;
        }
    }

    public int longestConsecutive(int[] nums) {
        int n = nums.length, ans = 0;
        UnionFind unionFind = new UnionFind();
        for (int num: nums) {
            unionFind.addNumber(num);
            if (unionFind.parents.containsKey(num - 1)) {
                unionFind.union(num, num - 1);
            }
            if (unionFind.parents.containsKey(num + 1)) {
                unionFind.union(num, num + 1);
            }
            int root = unionFind.root(num);
            int sizeOfRoot = unionFind.size.get(root);
            ans = Math.max(sizeOfRoot, ans);
        }
        return ans;
    }
}