package io.github.saikg.leetcode.s954;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        SortedMap<Integer, Integer> counter = Arrays.stream(arr).boxed()
                .collect(Collectors.toMap(Function.identity(), v -> 1, (a, b) -> a + b, TreeMap::new));

        int ks = counter.keySet().size(), i = 0;
        int[] keys = new int[ks];
        for (int key : counter.keySet()) {
            keys[i++] = key;
        }

        i = 0;
        while (i < ks) {
            int key = keys[i];
            if (counter.get(key) == 0) {
                i++;
                continue;
            }
            if (key < 0 && (key&1) == 1) {
                return false;
            }
            counter.put(key, counter.get(key) - 1);
            int pair = (key < 0) ? key / 2 : key * 2;
            int reps = counter.getOrDefault(pair, 0);
            if (reps == 0) {
                return false;
            }
            counter.put(pair, reps - 1);
        }
        return true;
    }
}