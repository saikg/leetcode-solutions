package io.github.saikg.leetcode.s843;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Solution {
    public void findSecretWord(String[] wordList, Master master) {
        int n = wordList.length;
        boolean[] elimination = new boolean[n];
        List<String> words = new ArrayList<>(Arrays.asList(wordList));
        for (int guessNo = 1; guessNo <= 10; guessNo++) {
            int mid = words.size() / 2;
            String guess = words.get(mid);
            int matches = master.guess(guess);
            if (matches == 6) {
                return;
            }
            words.removeIf(w -> match(w, guess) != matches || Objects.equals(w, guess));
        }
    }

    private int match(String a, String b) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            count += (a.charAt(i) == b.charAt(i)) ? 1 : 0;
        }
        return count;
    }
}