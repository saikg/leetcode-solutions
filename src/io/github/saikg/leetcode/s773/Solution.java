package io.github.saikg.leetcode.s773;

import java.util.*;

public class Solution {

    private static final String SOLVED_STATE = "123450";

    public int slidingPuzzle(int[][] board) {
        String encodedState = encodeState(board);
        if (encodedState.equals(SOLVED_STATE)) {
            return 0;
        }

        Map<String, Integer> movesReq = new HashMap<>();
        movesReq.put(encodedState, 0);

        Queue<int[][]> traversalQueue = new ArrayDeque<>();
        traversalQueue.add(board);
        while (!traversalQueue.isEmpty()) {
            int[][] candidate = traversalQueue.poll();
            List<int[][]> neighbours = getNeighbours(candidate);
            for (int[][] neighbour: neighbours) {
                String encoded = encodeState(neighbour);
                int dist = movesReq.get(encodeState(candidate)) + 1;
                if (encoded.equals(SOLVED_STATE)) {
                    return dist;
                }
                if (!movesReq.containsKey(encoded)) {
                    movesReq.put(encoded, dist);
                    traversalQueue.add(neighbour);
                }
            }
        }
        return -1;
    }

    private List<int[][]> getNeighbours(int[][] board) {
        int m = board.length, n = board[0].length;

        int r = -1, c = -1;
        for (int i = 0; i < m & r == -1; i++) {
            for (int j = 0; j < n && c == -1; j++) {
                if (board[i][j] == 0) {
                    r = i;
                    c = j;
                }
            }
        }

        int[][] dirs = new int[][]{
                {-1, 0}, {0, -1}, {0, 1}, {1, 0}
        };

        List<int[][]> neighbours = new ArrayList<>();
        for (int[] dir: dirs) {
            int dr = dir[0], dc = dir[1];
            if (r + dr < 0 || r + dr >= m || c + dc < 0 || c + dc >= n) {
                continue;
            }

            int[][] copy = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    copy[i][j] = board[i][j];
                }
            }

            int tmp = copy[r + dr][c + dc];
            copy[r + dr][c + dc] = copy[r][c];
            copy[r][c] = tmp;

            neighbours.add(copy);
        }
        return neighbours;
    }

    private String encodeState(int[][] board) {
        int m = board.length, n = board[0].length;
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                encoded.append(board[i][j]);
            }
        }
        return encoded.toString();
    }
}