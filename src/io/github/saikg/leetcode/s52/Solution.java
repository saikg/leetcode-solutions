package io.github.saikg.leetcode.s52;

public class Solution {

    int numSafePositions;

    public int totalNQueens(int boardSize) {
        numSafePositions = 0;
        int[] queenPositions = new int[boardSize];
        backtrack(0, boardSize, queenPositions);
        return numSafePositions;
    }

    private void backtrack(int queensPlaced, int boardSize, int[] queenPositions) {
        if (queensPlaced == boardSize) {
            numSafePositions++;
            return;
        }

        for (int queenPosition = 0; queenPosition < boardSize; queenPosition++) {
            if (isUnderAttack(queenPosition, queensPlaced, queenPositions, boardSize)) {
                continue;
            }

            queenPositions[queensPlaced] = queenPosition;
            backtrack(queensPlaced + 1, boardSize , queenPositions);
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
}