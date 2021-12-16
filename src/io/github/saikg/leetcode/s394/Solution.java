//package io.github.saikg.leetcode.s394;

public class Solution {

    StringBuilder stringBuilder = new StringBuilder();

    public String decodeString(String s) {
        return decodeString(s, 0);
    }

    private String decodeString(String s, int id) {
        System.out.println("id = " + id);
        if (id >= s.length()) {
            return "";
        }

        char ch = s.charAt(id);
        if (isLetter(ch)) {
            String t = readString(s, id);
            id += t.length();
            return t + decodeString(s, id);
        }

        int multiplier = readNumber(s, id);
        for (long i = 1; i <= multiplier; i *= 10) {
            id++;
        }
        id++; // opening bracket
        String t = decodeString(s, id);
        id++; // closing bracket
        return repeatedString(t, multiplier) + decodeString(s, id);
    }

    private boolean isLetter(char ch) {
        return between('a', 'z', ch);
    }

    private boolean isDigit(char ch) {
        return between('0', '9', ch);
    }

    private boolean between(char low, char high, char ch) {
        return ch - low >= 0 && high - ch >= 0;
    }

    private int readNumber(String s, int ip) {
        int ans = 0;
        while (ip < s.length() && isDigit(s.charAt(ip))) {
            ans *= 10;
            ans += s.charAt(ip++) - '0';
        }
        return ans;
    }

    private String readString(String s, int ip) {
        StringBuilder stringBuilder = new StringBuilder();
        while (ip < s.length() && isLetter(s.charAt(ip))) {
            stringBuilder.append(s.charAt(ip++));
        }
        System.out.println("stringBuilder.toString() = " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private String repeatedString(String t, int m) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            stringBuilder.append(t);
        }
        return stringBuilder.toString();
    }
}