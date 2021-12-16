package io.github.saikg.leetcode.s54;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        List<Integer> output = new ArrayList<>();
        int[][] dirs = new int[][]{
                {1, 0},
                {0, -1},
                {-1,  0},
                {0, 1}
        };
        int p = 0, r = 0, c = 0;
        int dx = dirs[p][0], dy = dirs[p][1];
        for (int i = 0; i < m*n; i++) {
            visited[r][c] = true;
            output.add(matrix[r][c]);
            int rd = r + dx;
            int cd = c + dy;
            if (rd < 0 || rd >= m || cd < 0 || cd >= n || visited[rd][cd]) {
                p = (p + 1) % 4;
                dx = dirs[p][0];
                dy = dirs[p][1];
            }
            r = r + dx;
            c = c + dy;
        }
        return output;
    }
}