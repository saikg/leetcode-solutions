package io.github.saikg.leetcode.s519;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution {

    private static final Random random = new Random();

    int rows, cols;
    Set<Integer> flipped;

    public Solution(int m, int n) {
        rows = m;
        cols = n;
        reset();
    }

    public int[] flip() {
        int high = (rows - 1) * cols + (cols - 1);

        int rand = random.nextInt(high + 1);
        while (flipped.contains(rand)) {
            rand = random.nextInt(high + 1);
        }

        flipped.add(rand);
        return new int[]{
                rand / cols,
                rand % cols
        };
    }

    public void reset() {
        flipped = new HashSet<>();
    }

}