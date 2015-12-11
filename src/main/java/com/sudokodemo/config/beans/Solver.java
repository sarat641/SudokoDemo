/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.beans;

import com.sudokodemo.config.api.Board;

/**
 *
 * @author SARAT
 */
public interface Solver {

    void printGrid(int[][] sudokoBoard);

    boolean solve(int[][] sudokoBoard);

    Boolean isValid(int[][] sudokoBoard);
    
}
