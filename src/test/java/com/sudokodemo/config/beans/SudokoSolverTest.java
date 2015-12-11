/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.beans;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author SARAT
 */
public class SudokoSolverTest {

    private SudokoSolver sudokoSolver;

    @Before
    public void init() {
        sudokoSolver = new SudokoSolver();
    }

    @Test
    public void solve() {
        String sudokoBoardAsString = "9..2....8..8...7.2...48..3.3..7.4..9..2.1.....8....4..8..1....4....4..7..9.8.3..5";
        String sudokoBoardExpectedString="913257648468391752527486931356724189742918563189635427875162394231549876694873215";
        SudokoBoard sudokoBoard1 = new SudokoBoard(1);
        sudokoBoard1.create(sudokoBoardAsString);
        sudokoSolver.solve(sudokoBoard1.getSolutionArray());
        Assert.assertEquals(sudokoBoardExpectedString,sudokoBoard1.getSolutionArrayAsString());
    }

    /**
     * Verifying whether given board is valid or not
     */
    @Test
    public void isValid() {
        String sudokoBoardAsString = "..5....3....9...8....57.....9.7....3.7.13..5.3.2......2...8......1..94259....78..";
        SudokoBoard sudokoBoard1 = new SudokoBoard(1);
        sudokoBoard1.create(sudokoBoardAsString);
        Assert.assertTrue(sudokoSolver.isValid(sudokoBoard1.getSolutionArray()));

        String sudokoExpertBoardAsString = "......86.8.9..5..3.....3..465.9.8.317..1.........3.7.5........7.8...94..1..5....6";
        SudokoBoard sudokoExpertBoard = new SudokoBoard(1);
        sudokoExpertBoard.create(sudokoExpertBoardAsString);
        Assert.assertTrue(sudokoSolver.isValid(sudokoExpertBoard.getSolutionArray()));

        String sudokoIntermediateBoardAsString = ".64...2..2.5..3.1......5..69...1.....8...9.4..5.....9..9....1.....26.....4..38...";
        SudokoBoard sudokoIntermediateBoard = new SudokoBoard(1);
        sudokoIntermediateBoard.create(sudokoIntermediateBoardAsString);
        Assert.assertTrue(sudokoSolver.isValid(sudokoIntermediateBoard.getSolutionArray()));

        String sudokoIntermediateBoardAsInvalidString = ".64...2..2.5..3.1......5..69...1.....8...9.4..5.....9..9....1.....26.....4..38..3";
        SudokoBoard sudokoIntermediateInvalidBoard = new SudokoBoard(1);
        sudokoIntermediateInvalidBoard.create(sudokoIntermediateBoardAsInvalidString);
        Assert.assertFalse(sudokoSolver.isValid(sudokoIntermediateInvalidBoard.getSolutionArray()));
    }
}
