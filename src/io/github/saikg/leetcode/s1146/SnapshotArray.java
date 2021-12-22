package io.github.saikg.leetcode.s1146;

import java.util.ArrayList;
import java.util.List;

public class SnapshotArray {

    int n, snapId;
    List<List<int[]>> data;

    public SnapshotArray(int length) {
        n = length;
        snapId = 0;
        data = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            data.add(new ArrayList<>());
            data.get(i).add(new int[]{snapId, 0});
        }
    }

    public void set(int index, int val) {
        List<int[]> versions = data.get(index);
        int k = versions.size();
        int[] lastUpdated = versions.get(k-1);
        if (lastUpdated[0] == snapId) {
            lastUpdated[1] = val;
        } else {
            versions.add(new int[]{snapId, val});
        }
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        List<int[]> versions = data.get(index);
        int low = 0, high = versions.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int[] version = versions.get(mid);
            if (version[0] == snap_id) {
                return version[1];
            } else if (version[0] < snap_id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return versions.get(high)[1];
    }
}