/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.api;

/**
 *
 * @author SARAT
 */
public interface Board {

    public void create(String boardAsString);

    void setValueInCell(int value, int row, int col);

    int getValueInCell(int row, int col);

    int getSize();

    int[][] getCells();

    String getBoardAsString();

    Integer getId();

    void logUserMovies(int row, int col, int cellValue);

    Boolean getHavingSolution();

    void setHavingSolution(Boolean havingSolution);

    boolean isGameFinished();

    int[][] getSolutionArray();

    int getValueInSolutionArray(int row, int col);

    String getSolutionArrayAsString();

    public Boolean getChached();

    public void setChached(Boolean chached);
}
