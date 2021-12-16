package io.github.saikg.leetcode.s981;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {

    private Map<String, List<Entry>> data;

    public TimeMap() {
        data = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<Entry> entries = data.computeIfAbsent(key, v -> new ArrayList<>());
        int idx = searchLowerBound(entries, timestamp);
        if (idx < entries.size() && entries.get(idx).timestamp == timestamp) {
            entries.get(idx).value = value;
        } else {
            entries.add(idx + 1, new Entry(value, timestamp));
        }
    }

    public String get(String key, int timestamp) {
        return "";
    }

    private int searchExact(List<Entry> entries, int timestamp) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).timestamp == timestamp) {
                return i;
            }
        }
        return -1;
    }

    private int searchLowerBound(List<Entry> entries, int timestamp) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).timestamp > timestamp) {
                return i-1;
            }
        }
        return -1;
    }

    class Entry {
        String value;
        int timestamp;
        Entry(String v, int t) {
            value = v;
            timestamp = t;
        }
    }
}
