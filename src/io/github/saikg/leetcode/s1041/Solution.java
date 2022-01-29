package io.github.saikg.leetcode.s1041;

import java.util.Arrays;

public class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] pos = {0, 0};
        int[] dir = {0, 1};
        for (char ch : instructions.toCharArray()) {
            switch (ch) {
                case 'G':
                    pos[0] += dir[0];
                    pos[1] += dir[1];
                    break;
                case 'L':
                    dir = turnLeft(dir);
                    break;
                case 'R':
                    dir = turnRight(dir);
                    break;
            }
        }
        System.out.println(Arrays.toString(pos));
        System.out.println(Arrays.toString(dir));

        return formsCircle(pos, dir);
    }

    private boolean formsCircle(int[] pos, int[] dir) {
        if (dir[0] != 0 || dir[1] != 1) {
            return true;
        }
        return (pos[0] == 0 && pos[1] == 0);
    }

    private int[] turnLeft(int[] dir) {
        return new int[]{
                dir[1], -dir[0]
        };
    }

    private int[] turnRight(int[] dir) {
        return new int[]{
                -dir[1], dir[0]
        };
    }
}