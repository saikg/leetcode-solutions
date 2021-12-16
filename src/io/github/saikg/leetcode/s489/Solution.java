package io.github.saikg.leetcode.s489;

public class Solution {
    public void cleanRoom(Robot robot) {
        robot.clean();
        boolean moved = robot.move();
        if (!moved) {
            robot.turnLeft();
        }
        cleanRoom(robot);
    }

    enum Dir {
        UP(0, 1),
        DOWN(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        int x, y;
        Dir(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}