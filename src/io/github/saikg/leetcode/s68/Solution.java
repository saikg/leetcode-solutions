package io.github.saikg.leetcode.s68;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        int[] wordLengths = new int[n];
        for (int i = 0; i < n; i++) {
            wordLengths[i] = words[i].length();
        }
        int sentenceLength = 0;
        List<String> paragraph = new ArrayList<>();
        List<String> wordsInSentence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int wordWithSpace = words[i].length();
            if (sentenceLength > 0) {
                wordWithSpace++;
            }
            if (sentenceLength + wordWithSpace <= maxWidth) {
                sentenceLength += wordWithSpace;
                wordsInSentence.add(words[i]);
            } else {
                paragraph.add(justify(wordsInSentence, maxWidth));
                // new line
                sentenceLength = words[i].length();
                wordsInSentence.clear();
                wordsInSentence.add(words[i]);
            }
        }

        paragraph.add(leftJustify(wordsInSentence, maxWidth));
        return paragraph;
    }

    private String justify(List<String> words, int width) {
        int n = words.size();
        int allWordsLength = words.stream()
                .map(String::length)
                .reduce(Integer::sum)
                .get();
        int totalSpaces = width - allWordsLength;
        if (n == 1) {
            return leftJustify(words, width);
        }

        int spacesBetween = totalSpaces / (n-1);
        int extraSpaces = (totalSpaces % (n-1));

        StringBuilder sentenceBuilder = new StringBuilder();
        for (int i = 0; i < n-1; i++) {
            sentenceBuilder.append(words.get(i));
            for (int s = 0; s < spacesBetween; s++) {
                sentenceBuilder.append(" ");
            }
            if (i < extraSpaces) {
                sentenceBuilder.append(" ");
            }
        }
        sentenceBuilder.append(words.get(n-1));
        return sentenceBuilder.toString();
    }

    private String leftJustify(List<String> wordsInLine, int width) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < wordsInLine.size(); i++) {
            if (i != 0) {
                line.append(" ");
            }
            line.append(wordsInLine.get(i));
        }

        while (line.length() < width) {
            line.append(" ");
        }
        return line.toString();
    }
}