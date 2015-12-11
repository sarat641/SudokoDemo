package com.sudokodemo.config.beans;

import com.sudokodemo.config.api.Board;
import java.util.Arrays;

/**
 *
 * @author SARAT
 */
public class SudokoBoard implements Board {

    private static final int SIZE = 9;
    private final Integer id;
    private int[][] cells = new int[SIZE][SIZE];
    private Boolean havingSolution = Boolean.FALSE;
    private int[][] solutionArray = new int[SIZE][SIZE];// holds final solution of this board
    private Boolean chached = Boolean.FALSE; // work arround 

    public SudokoBoard(Integer id) {
        this.id = id;
    }

    @Override
    public void create(String boardAsSingleLine) {
        for (int i = 0; i < 81; i++) {
            char c = boardAsSingleLine.charAt(i);
            int row = i / 9;
            int col = i % 9;
            if (c != '.') {
                setValueInCell(c - '0', row, col);
            }
        }
    }

    @Override
    public void setValueInCell(int value, int row, int col) {
        cells[row][col] = value;
        solutionArray[row][col] = value;;

    }

    @Override
    public int[][] getCells() {
        return cells;
    }

    @Override
    public int getValueInCell(int row, int col) {
        return cells[row][col];
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getBoardAsString() {

        return getArrayAsString(cells);
    }

    private String getArrayAsString(int[][] array) {
        StringBuilder boardAsString = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boardAsString.append(array[i][j]);
            }
        }
        return boardAsString.toString();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void logUserMovies(int row, int col, int cellValue) {
        setValueInCell(cellValue, row, col);
    }

    @Override
    public Boolean getHavingSolution() {
        return havingSolution;
    }

    @Override
    public void setHavingSolution(Boolean havingSolution) {
        this.havingSolution = havingSolution;
    }

    @Override
    public boolean isGameFinished() {
        return Arrays.deepEquals(solutionArray, cells);
    }

    @Override
    public int[][] getSolutionArray() {
        return solutionArray;
    }

    @Override
    public String getSolutionArrayAsString() {
        return getArrayAsString(solutionArray);
    }

    @Override
    public int getValueInSolutionArray(int row, int col) {
        return solutionArray[row][col];
    }

    public Boolean getChached() {
        return chached;
    }

    public void setChached(Boolean chached) {
        this.chached = chached;
    }

    
}
