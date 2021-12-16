package io.github.saikg.leetcode.s48;

public class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        for (int i = 0; i < matrix.length; i++) {
            reverseRow(matrix, i);
        }
    }

    private void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private void reverseRow(int[][] matrix, int row) {
        int s = 0, e = matrix.length - 1;
        while (s < e) {
            swap(matrix, row, s++, row, e--);
        }
    }

    private void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int t = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = t;
    }
}