package io.github.saikg.leetcode.s1886;

public class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int[][] matMetadata = getMetadata(mat);
        int[][] targetMetadata = getMetadata(target);
        for (int i = 0; i < 4; i++) {
            matMetadata = rotate(matMetadata[0], matMetadata[1]);
            if (match(matMetadata, targetMetadata)) {
                return true;
            }
        }
        return false;
    }

    private int[][] getMetadata(int[][] mat) {
        int n = mat.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        for (int i = 0; i < n; i++) {
            rows[i] = convertRowToInt(mat, i);
            cols[i] = convertColToInt(mat, i);
        }
        return new int[][]{
                rows, cols
        };
    }

    private int convertRowToInt(int[][] mat, int row) {
        int dec = 0, n = mat.length;
        for (int i = 0; i < n; i++) {
            dec = dec*2 + mat[row][i];
        }
        return dec;
    }

    private int convertColToInt(int[][] mat, int col) {
        int dec = 0, n = mat.length;
        for (int i = 0; i < n; i++) {
            dec = dec*2 + mat[i][col];
        }
        return dec;
    }

    private int[][] rotate(int[] rows, int[] cols) {
        reverse(rows);
        return new int[][]{
                cols, rows
        };
    }

    private void reverse(int[] arr) {
        int e = arr.length - 1, s = 0;
        while (s < e) {
            swap(arr, s++, e--);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    private boolean match(int[][] sourceMetadata, int[][] targetMetadata) {
        int n = sourceMetadata[0].length;
        for (int i = 0; i < n; i++) {
            if (sourceMetadata[0][i] != targetMetadata[0][i]) {
                return false;
            }
            if (sourceMetadata[1][i] != targetMetadata[1][i]) {
                return false;
            }
        }
        return true;
    }
}