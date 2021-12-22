package io.github.saikg.leetcode.s1884;

public class Solution {
    public int twoEggDrop(int n) {
        return (int)Math.ceil((Math.sqrt(8*n + 1)  - 1)/ 2);
    }
}