package io.github.saikg.leetcode.s28;

public class Solution {
    public int strStr(String haystack, String needle) {
        return bruteForceSearch(haystack, needle);
    }

    /*
    * Time complexity: O(nm)
    * Space complexity: O(1)
    * */
    private int bruteForceSearch(String haystack,
                                 String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        for (int hi = 0; hi <= hl-nl; hi++) {
            boolean match = true;
            for (int ni = 0; ni < nl && match; ni++) {
                if (haystack.charAt(hi+ni) != needle.charAt(ni)) {
                    match = false;
                }
            }
            if (match) {
                return hi;
            }
        }
        return 0;
    }

    private int knuthMorrisPrat(String haystack,
                                String needle) {
        return 0;
    }
}