package io.github.saikg.leetcode.util;

import java.util.Arrays;
import java.util.Random;

public class Generator {

    private static final Random random = new Random();

    public int intInRange(int a, int b) {
         return a + (int)((b - a) * random.nextDouble());
    }

    public int[] randomArray(int n, int a, int b) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = intInRange(a, b);
        }
        return arr;
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        int[] t = generator.randomArray(10, 0, 2);
        int p = generator.intInRange(0, 2);
    }
}
