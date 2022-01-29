package io.github.saikg.leetcode.s134;

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        return optimised(gas, cost);
    }

    private int optimised(int[] gas, int[] cost) {
        int n = gas.length;

        int fuel = 0, maxFuel = 0, result = -1;
        int[] suffix = new int[n];
        for (int i = n-1; i >= 0; i--) {
            suffix[i] = fuel + gas[i] - cost[i];
            fuel = suffix[i];
            if (fuel > maxFuel) {
                maxFuel = fuel;
                result = i;
            }
        }

        return result;
    }
}