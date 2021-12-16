package io.github.saikg.leetcode.s2007;

import java.util.*;

public class Solution {

    private static final int[] EMPTY = new int[]{};

    public int[] findOriginalArray(int[] changed) {
        SortedMap<Integer, Integer> counter = new TreeMap<>();
        for (int num: changed) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[changed.length / 2];
        int id = 0;
        Collection<Integer> keys = counter.keySet();
        for (int key: keys) {
            while (counter.get(key) > 0) {
                counter.put(key, counter.get(key) - 1);
                int pair = 2 * key;
                if (counter.getOrDefault(pair, 0) == 0) {
                    return EMPTY;
                }
                counter.put(pair, counter.get(pair) - 1);
                result[id++] = key;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.findOriginalArray(new int[]{2,1,2,4,2,4})));
    }
}
