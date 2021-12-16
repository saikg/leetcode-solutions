package io.github.saikg.leetcode.s342;

public class Solution {
    public boolean isPowerOfFour(int n) {
//        return isPowerOfTwo(n) && ((n & 0xaaaaaaaa) == 0);
        return isPowerOfTwo(n) && (n % 3 == 1);
    }

    private boolean isPowerOfTwo(int n) {
        return (n != 0) && ((n & (n - 1)) == 0);
    }
}
