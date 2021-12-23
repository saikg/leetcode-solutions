package io.github.saikg.leetcode.s732;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyCalendarThree {

    private List<Pair<Integer, Integer>> bookingEvents;

    public MyCalendarThree() {
        bookingEvents = new ArrayList<>();
    }

    public int book(int start, int end) {
        int s = bookingEvents.size();

        int startInsertPosition = getInsertionPoint(bookingEvents, start, 1);
        bookingEvents.add(startInsertPosition, new Pair<>(start, 1));

        int endInsertPosition = getInsertionPoint(bookingEvents, end, 0);
        bookingEvents.add(endInsertPosition, new Pair<>(end, 0));

        int open = 0, ans = 0;
        for (Pair<Integer, Integer> p : bookingEvents) {
            if (p.getValue() == 1) {
                open++;
            } else {
                open--;
            }
            ans = Math.max(ans, open);
        }
        return ans;
    }

    private int getInsertionPoint(List<Pair<Integer, Integer>> list, Integer f, Integer type) {
        int p = Collections.binarySearch(list, new Pair<>(f, type),
                Comparator.comparingInt((Pair<Integer, Integer> u) -> u.getKey())
                        .thenComparingInt(Pair::getValue)
        );
        return p < 0 ? -(p + 1) : p;
    }
}