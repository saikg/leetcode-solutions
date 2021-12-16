package io.github.saikg.leetcode.s878;

public class Solution {
    private static final int MOD = 1_000_000_007;

    public int nthMagicalNumber(int n, int a, int b) {
        if (a > b) {
            return nthMagicalNumber(n, b, a);
        }

        long low = a, high = (long)b * n;
//        return linearSearch(a, b, low, high, n);
        return (int)(binarySearch(a, b, low, high, n) % MOD);
    }

    private int linearSearch(int a, int b, int low, int high, int n) {
        for (int i = low; i < high; i++) {
            if (multiplesBelow(a, b, i) == n) {
                return i;
            }
        }
        return -1;
    }

    private long binarySearch(int a, int b, long low, long high, int n) {
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long mb = multiplesBelow(a, b, mid);
            if (mb >= n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high + 1;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int t = b % a;
            b = a;
            a = t;
        }
        return b;
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private long multiplesBelow(int a, int b, long p) {
        return p / a + p / b - p / lcm(a, b);
    }

    public static void main(String[] args) {
        int a = 5, b = 7, n = 4;
        Solution solution = new Solution();
        for (int i = 1; i < 10; i++) {
            int ans =  solution.nthMagicalNumber(i, a, b);
            System.out.println("ans = " + ans);
        }
    }
}