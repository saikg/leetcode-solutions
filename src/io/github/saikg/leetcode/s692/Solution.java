package io.github.saikg.leetcode.s692;

import java.util.*;

public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> mostFrequent = new PriorityQueue<>((a, b) -> {
            int valueOrder = Integer.compare(frequencyMap.get(a), frequencyMap.get(b));
            if (valueOrder != 0) {
                return valueOrder;
            }
            return b.compareTo(a);
        });

        for (String key : frequencyMap.keySet()) {
            mostFrequent.add(key);
            if (mostFrequent.size() > k) {
                mostFrequent.remove();
            }
        }

        List<String> topElements = new LinkedList<>();
        while (!mostFrequent.isEmpty()) {
            topElements.add(0, mostFrequent.poll());
        }
        return topElements;
    }
}