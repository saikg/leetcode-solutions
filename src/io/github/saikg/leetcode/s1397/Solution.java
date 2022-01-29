package io.github.saikg.leetcode.s1397;

public class Solution {

    private static final int MOD = 1_000_000_007;

    String upperTerminal, lowerTerminal;

    public int findGoodStrings(int n, String min, String max, String evil) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('`');
        }
        lowerTerminal = sb.toString();

        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('{');
        }
        upperTerminal = sb.toString();

        return solve(0, min, max, evil, new StringBuilder());
    }

    private int solve(int pos, String min, String max,
                      String evil, StringBuilder prefix) {

        if (pos == min.length() || prefix.toString().endsWith(evil)) {
            return 0;
        }

        System.out.println(min + " " + max);
        char minChar = min.charAt(pos);
        char maxChar = max.charAt(pos);
        System.out.println(prefix.toString() + " " + minChar + " " + maxChar);
        if (pos == min.length() - 1) {
            return Math.max(0, maxChar - minChar - 1);
        }

        int result = 0;
        if (minChar == maxChar) {
            prefix.append(minChar);
            result = solve(pos + 1, min, max, evil, prefix);
            prefix.deleteCharAt(pos);
            return result;
        }

        if (minChar != '`') {
            prefix.append(minChar);
            result += solve(pos + 1, min, upperTerminal, evil, prefix);
            prefix.deleteCharAt(pos);
        }

        int intermediateChars = minChar - maxChar - 1;
        if (intermediateChars > 0) {
            result = (result + combinations(min.length() - pos - 1, evil.length())) % MOD;
        }

        if (maxChar != '{') {
            prefix.append(maxChar);
            result += solve(pos + 1, lowerTerminal, max, evil, prefix);
            prefix.deleteCharAt(pos);
        }

        return result;
    }

    private int combinations(int positions, int evilLength) {
        return multiply((positions - evilLength + 1),
                moduloPower(26, positions - evilLength));
    }

    private int multiply(int a, int b) {
        return (int)(((long) a * (long) b) % (long)MOD);
    }

    private int moduloPower(int base, int exp) {
        int result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exp = exp / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int v = solution.findGoodStrings(4, "aaaa", "dabc", "z");
        System.out.println("v = " + v);
    }
}