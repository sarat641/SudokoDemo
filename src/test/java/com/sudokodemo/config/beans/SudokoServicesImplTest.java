package com.sudokodemo.config.beans;

import com.sudokodemo.config.Application;
import com.sudokodemo.config.api.SudokoService;
import com.sudokodemo.config.dto.BasicSudokoDTO;
import com.sudokodemo.config.dto.SudokoDTO;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author SARAT
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class SudokoServicesImplTest {

    @Autowired
    SudokoService sudokoService;

    @Test
    public void sudokoServiceShouldNotBeNull() {
        assertNotNull(sudokoService);
    }

    @Test
    public void validateSudokoBoard() {
        String sudokoBoardAsString = "..5....3....9...8....57.....9.7....3.7.13..5.3.2......2...8......1..94259....78..";
        BasicSudokoDTO inputSudokoDTO = new BasicSudokoDTO();
        inputSudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);

        BasicSudokoDTO outputSudokoDTO = sudokoService.createSudokoBoard(inputSudokoDTO);

        assertTrue(outputSudokoDTO.getIsValidBoard());

    }

    @Test
    public void solveSudoko() {
        String sudokoBoardAsString = "9..2....8..8...7.2...48..3.3..7.4..9..2.1.....8....4..8..1....4....4..7..9.8.3..5";
        BasicSudokoDTO sudokoDTO = new BasicSudokoDTO();
        sudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);
        sudokoDTO = sudokoService.createSudokoBoard(sudokoDTO);

        String solution = "913257648468391752527486931356724189742918563189635427875162394231549876694873215";
        BasicSudokoDTO outputSudokoDTO = sudokoService.solveSudoko(sudokoDTO.getId());
        assertTrue(outputSudokoDTO.getSolutionExists());
        assertEquals(solution, outputSudokoDTO.getSudokoBoadSolutionString());

    }

    @Test
    public void successiveMoves() {

        String sudokoBoardAsString = "9..2....8..8...7.2...48..3.3..7.4..9..2.1.....8....4..8..1....4....4..7..9.8.3..5";
        BasicSudokoDTO basicSudokoDTO = new BasicSudokoDTO();
        basicSudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);
        basicSudokoDTO = sudokoService.createSudokoBoard(basicSudokoDTO);

        SudokoDTO sudokoDTO = new SudokoDTO();
//        sudokoDTO.setSudokoBoardAsString(sudokoBoardAsString);
        sudokoDTO.setRow(0);
        sudokoDTO.setColumn(1);
        sudokoDTO.setCellValue(1);
        sudokoDTO.setId(basicSudokoDTO.getId());
        String currentSudokoBoardAsString = "913257648468391752527486931356724189742918563189635427875162394231549876694873215";
//        sudokoDTO.setCurrentSudokoBoardAsString(currentSudokoBoardAsString);

        SudokoDTO outputSudokoDTO = sudokoService.successiveMoves(sudokoDTO);
        assertTrue(outputSudokoDTO.getIsValidMove());

    }
}
