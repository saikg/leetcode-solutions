package io.github.saikg.leetcode.s67;

public class Solution {
    public String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        if (lenA < lenB) {
            return addBinary(b, a);
        }

        int carry = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lenB; i++) {
            int v = (a.charAt(lenA - 1 - i) - '0') +
                    (b.charAt(lenB - 1 - i) - '0') + carry;
            builder.append(v % 2);
            carry = carry / 2;
            carry += v / 2;
        }

        for (int i = lenB; i < lenA; i++) {
            int v = (a.charAt(lenA - 1 - i) - '0') + carry;
            builder.append(v % 2);
            carry /= 2;
            carry += v / 2;
        }

        while (carry != 0) {
            builder.append(carry % 2);
            carry = carry / 2;
        }

        return builder.reverse().toString();
    }
}
