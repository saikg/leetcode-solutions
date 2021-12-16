package io.github.saikg.leetcode.s957;

import java.util.Arrays;

public class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int L = cells.length;

        int[] bitmask = new int[1 << L];
        Arrays.fill(bitmask, -1);
        bitmask[convertToInt(cells)] = 0;

        int cycleStart = -1, cycleLength = -1;
        for (int i = 1; i <= n; i++) {
            nextDay(cells);
            int dec = convertToInt(cells);
            if (bitmask[dec] != -1) {
                cycleStart = bitmask[dec];
                cycleLength = i - bitmask[dec];
                break;
            }
            bitmask[dec] = i;
        }

        if (cycleLength == -1) {
            return cells;
        }

        n = n - cycleStart;
        int pos = n % cycleLength + cycleStart;
        for (int i = 0; i < (1 << L); i++) {
            if (pos == bitmask[i]) {
                cells = convertToBinary(i, L);
                break;
            }
        }
        return cells;
    }

    private void nextDay(int[] cells) {
        int L = cells.length;
        int prev = cells[0];

        cells[0] = 0;
        for (int i = 1; i < L - 1; i++) {
            int curr = cells[i];
            cells[i] = (prev + cells[i+1] != 1) ? 1 : 0;
            prev = curr;
        }
        cells[L-1] = 0;
    }

    private int convertToInt(int[] cells) {
        int b = 0, p = 1, L = cells.length;
        for (int i = L - 1; i >= 0; i--) {
            b += cells[i] * p;
            p = p << 1;
        }
        return b;
    }

    private int[] convertToBinary(int v, int L) {
        int[] cells = new int[L];
        for (int i = L - 1; i >= 0; i--) {
            cells[i] = v % 2;
            v = v / 2;
        }
        return cells;
    }
}