package io.github.saikg.leetcode.s51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    List<List<String>> safePositions;

    public List<List<String>> solveNQueens(int n) {
        System.out.println(createBoard(new int[]{2, 1, 2}));

        int[] f = new int[n];
        Arrays.fill(f, -1);

        safePositions = new ArrayList<>();
        backtrack(0, n, f);
        return safePositions;
    }

    private void backtrack(int queensPlaced, int boardSize, int[] f) {
        if (queensPlaced == boardSize) {
            safePositions.add(createBoard(f));
            return;
        }

        for (int queenPosition = 0; queenPosition < boardSize; queenPosition++) {
            if (isUnderAttack(queenPosition, queensPlaced, f, boardSize)) {
                continue;
            }

            f[queensPlaced] = queenPosition;
            backtrack(queensPlaced + 1, boardSize , f);
        }
    }

    private boolean isUnderAttack(int queenPosition, int placedQueens,
                                  int[] placedQueensPos,  int boardSize) {

        for (int row = 0; row < placedQueens; row++) {
            int deltaCols = Math.abs(queenPosition - placedQueensPos[row]);
            int deltaRows = placedQueens - row;
            if ((deltaRows == deltaCols) || (placedQueensPos[row] == queenPosition)) {
                return true;
            }
        }
        return false;
    }

    private List<String> createBoard(int[] f) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < f.length; i++) {
            int v = f[i];
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < v; j++) {
                stringBuilder.append('.');
            }
            stringBuilder.append('Q');
            for (int j = v + 1; j < f.length; j++) {
                stringBuilder.append('.');
            }
            board.add(stringBuilder.toString());
        }
        return board;
    }


}