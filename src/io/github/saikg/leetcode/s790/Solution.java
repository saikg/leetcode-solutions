package io.github.saikg.leetcode.s790;

public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final long[][] MAT = {
            {2, 0, 1},
            {1, 0, 0},
            {0, 1, 0}
    };
    private static final long[][] BASE = {
            {5},
            {2},
            {1}
    };

    public int numTilings(int n) {
        if (n < 3) {
            return n;
        }

        long[][] res = exponentiation(MAT, n-3);
        return (int)(matrixMultiplication(res, BASE)[0][0] % MOD);
    }

    private long[][] exponentiation(long[][] A, int n) {
        if (n == 1) {
            return A;
        }
        long[][] res = exponentiation(matrixMultiplication(A, A), n / 2);
        if (n % 2 == 1) {
            res = matrixMultiplication(A, res);
        }
        return res;
    }

    private long[][] matrixMultiplication(long[][] A, long[][] B) {
        int m = A.length, n = A[0].length, p = B[0].length;
        long[][] res = new long[m][p];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < p; k++) {
                long v = 0;
                for (int j = 0; j < n; j++) {
                    v = (v + A[i][j] * B[j][k]) % MOD;
                }
                res[i][k] = v;
            }
        }
        return res;
    }
}