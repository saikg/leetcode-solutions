package io.github.saikg.leetcode.s1146;

import java.util.*;

public class SnapshotArray {

    int n, snap = 0;
    List<List<int[]>> versions;

    public SnapshotArray(int length) {
        n = length;
        versions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            versions.add(new ArrayList<>());
        }
    }

    public void set(int index, int val) {
        List<int[]> p = versions.get(index);
        if (!p.isEmpty() && p.get(p.size() - 1)[0] == snap) {
            p.get(p.size() - 1)[1] = val;
        } else {
            versions.get(index).add(new int[]{snap, val});
        }
    }

    public int snap() {
        return snap++;
    }

    public int get(int index, int snap_id) {
        List<int[]> p = versions.get(index);
        if (p.isEmpty() || p.get(0)[0] > snap_id) {
            return 0;
        }

        int low = 0, high = p.size() - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            int v = p.get(mid)[0];

            if (v ==  snap_id) {
                return p.get(mid)[1];
            } else if (v < snap_id) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return p.get(low)[1];
    }
}