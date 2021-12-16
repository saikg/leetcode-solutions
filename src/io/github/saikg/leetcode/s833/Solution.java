package io.github.saikg.leetcode.s833;

import java.util.Arrays;
import java.util.Comparator;


public class Solution {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = indices.length;
        if (n == 0) {
            return s;
        }

        Operation[] operations = new Operation[n];
        for (int i = 0; i < n; i++) {
            operations[i] = new Operation(indices[i], sources[i], targets[i]);
        }
        Arrays.sort(operations, Comparator.comparingInt(a -> a.id));

        int i = 0, k = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i < s.length()) {
            if (k == n || i != operations[k].id) {
                stringBuilder.append(s.charAt(i));
                i++;
                continue;
            }

            Operation operation = operations[k++];
            if (matches(operation.pattern, s, i)) {
                stringBuilder.append(operation.target);
                i += operation.pattern.length();
            } else {
                stringBuilder.append(s.charAt(i));
                i++;
            }
        }
        return stringBuilder.toString();
    }

    private boolean matches(String pattern, String text, int begin) {
        if (text.length() - begin < pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != text.charAt(i + begin)) {
                return false;
            }
        }
        return true;
    }

    private class Operation {
        int id;
        String pattern;
        String target;
        Operation(int id, String source, String target) {
            this.id = id;
            this.pattern = source;
            this.target = target;
        }
    }
}