package io.github.saikg.leetcode.s1286;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/* Algorithm L implementation; Lexicographic combinations by D.E.Knuth
 */
public class CombinationIterator2 {

    private int[] nums;
    private int n, k;
    private String chars;
    private boolean nextAvailable;

    public CombinationIterator2(String characters, int combinationLength) {
        n = characters.length();
        k = combinationLength;
        chars = characters;

        nums = new int[k+1];
        for (int i = 0; i < k; i++) {
            nums[i] = i;
        }
        nums[k] = n;
        nextAvailable = true;
    }

    public String next() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = k-1; i >= 0; i--) {
            stringBuilder.append(chars.charAt(n - 1 - nums[i]));
        }

        int j = 0;
        for (; (j < k) && (nums[j+1] == nums[j] + 1); j++) {
            nums[j] = j;
        }
        nums[j]++;
        nextAvailable = j < k;

        return stringBuilder.toString();
    }

    public boolean hasNext() {
        return nextAvailable;
    }

    public static void main(String[] args) {
        CombinationIterator2 combinationIterator2 = new CombinationIterator2("abcd", 2);
        System.out.println(combinationIterator2.hasNext());
        System.out.println("next : " + combinationIterator2.next());
    }
}
