package io.github.saikg.leetcode.s79;

public class Solution {
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j] && backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int r, int c, int pos) {
        if (pos == word.length()) {
            return true;
        }

        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || visited[r][c]) {
            return false;
        }

        boolean ans = false;
        visited[r][c] = true;
        if (board[r][c] == word.charAt(pos)) {
            ans = backtrack(board, word, r + 1, c, pos + 1) ||
                    backtrack(board, word, r, c + 1, pos + 1) ||
                    backtrack(board, word, r - 1, c, pos + 1) ||
                    backtrack(board, word, r, c - 1, pos + 1);
        }
        visited[r][c] = false;
        return ans;
    }
}