package io.github.saikg.leetcode.s1061;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    int[] parent, size;

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();

        int m = 26;
        parent = new int[m];
        size = new int[m];
        for (int i = 0; i < m; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            int offset1 = s1.charAt(i) - 'a';
            int offset2 = s2.charAt(i) - 'a';
            union(offset1, offset2);
        }

        Map<Integer, Integer> rootToLowestChar = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int root = root(i);
            if (!rootToLowestChar.containsKey(root))
                rootToLowestChar.put(root, root);
            int lowest = Math.min(rootToLowestChar.get(root), i);
            rootToLowestChar.put(root, lowest);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (char ch: baseStr.toCharArray()) {
            stringBuilder.append((char)(rootToLowestChar.get(root(ch-'a')) + 'a'));
        }
        return stringBuilder.toString();
    }

    int root(int a) {
        while (parent[a] != a) {
            parent[a] = parent[parent[a]];
            a = parent[a];
        }
        return a;
    }

    void union(int a, int b) {
        int rootA = root(a);
        int rootB = root(b);
        if (size[rootA] > size[rootB]) {
            parent[rootB] = parent[rootA];
            size[rootA] += size[rootB];
        } else {
            parent[rootA] = parent[rootB];
            size[rootB] += size[rootA];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ans = solution.smallestEquivalentString("parker", "morris", "parser");
        System.out.println("ans = " + ans);
    }
}
