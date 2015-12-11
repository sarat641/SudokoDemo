
package com.sudokodemo.config.beans;

import java.util.Arrays;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAT
 */
@Component
public class SudokoSolver implements Solver {

    public SudokoSolver() {
    }

    @Override
    public void printGrid(int[][] sudokoBoard) {
        for (int i = 0; i < sudokoBoard.length; i++) {
            for (int j = 0; j < sudokoBoard.length; j++) {
                System.out.println(sudokoBoard[i][j]);
            }
            System.out.println("");
        }
    }

    @Override
    public boolean solve(int[][] sudokoBoard) {

        int[] temp = findUnAssignedColumnRow(sudokoBoard);

        if (temp != null) {
            int row = temp[0];
            int col = temp[1];

            for (int i = 1; i <= 9; i++) {
                if (isSafe(sudokoBoard, row, col, i)) {

                    sudokoBoard[row][col] = i;
                    if (solve(sudokoBoard)) {
                        return true;
                    }
                    sudokoBoard[row][col] = 0;
                }
            }
        } else {
            return true;
        }
        return false;

    }

    public boolean isSafe(int[][] sudokoBoard, int row, int col, int num) {

        return checkRow(sudokoBoard, row, col, num
        ) && checkCol(sudokoBoard, row, col, num
        ) && checkGrid(sudokoBoard, row, col, num
        );
    }

    public boolean checkRow(int[][] sudokoBoard, int row, int col, int num) {

        for (int i = 0; i < sudokoBoard.length; i++) {
            if (sudokoBoard[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCol(int[][] sudokoBoard, int row, int col, int num) {

        for (int[] sudokoBoard1 : sudokoBoard) {
            if (sudokoBoard1[col] == num) {
                return false;
            }
        }

        return true;
    }

//check grid
//change row & col to find start of box
    public boolean checkGrid(int[][] sudokoBoard, int row, int col, int num) {
        row = row - (row % 3);
        col = col - (col % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudokoBoard[i + row][j + col] == num) {
                    return false;
                }
            }
        }
        return true;

    }

    public int[] findUnAssignedColumnRow(int[][] sudokoBoard) {

        int[] t = new int[2];
        for (int i = 0; i < sudokoBoard.length; i++) {

            for (int j = 0; j < sudokoBoard.length; j++) {
                if (sudokoBoard[i][j] == 0) {
                    t[0] = i;
                    t[1] = j;
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public Boolean isValid(int[][] sudokoBoard) {

        int N = sudokoBoard.length;

        boolean[] b = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sudokoBoard[i][j] == 0) {
                    continue;
                }
                if (b[sudokoBoard[i][j]]) {
                    return false;
                }
                b[sudokoBoard[i][j]] = true;
            }
            Arrays.fill(b, false);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (sudokoBoard[j][i] == 0) {
                    continue;
                }
                if (b[sudokoBoard[j][i]]) {
                    return false;
                }
                b[sudokoBoard[j][i]] = true;
            }
            Arrays.fill(b, false);
        }

        int side = (int) Math.sqrt(N);

        for (int i = 0; i < N; i += side) {
            for (int j = 0; j < N; j += side) {
                for (int d1 = 0; d1 < side; d1++) {
                    for (int d2 = 0; d2 < side; d2++) {
                        if (sudokoBoard[i + d1][j + d2] == 0) {
                            continue;
                        }
                        if (b[sudokoBoard[i + d1][j + d2]]) {
                            return false;
                        }
                        b[sudokoBoard[i + d1][j + d2]] = true;
                    }
                }
                Arrays.fill(b, false);
            }
        }
        return true;
    }
}
