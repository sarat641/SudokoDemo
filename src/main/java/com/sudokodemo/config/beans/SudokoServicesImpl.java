package com.sudokodemo.config.beans;

import com.sudokodemo.config.exceptions.SudokoBoardNotFoundException;
import com.sudokodemo.config.api.Board;
import com.sudokodemo.config.api.BoardRepository;
import com.sudokodemo.config.api.SudokoService;
import com.sudokodemo.config.dto.BasicSudokoDTO;
import com.sudokodemo.config.dto.SudokoDTO;
import com.sudokodemo.config.exceptions.NotValidBoardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAT
 */
@Component
public class SudokoServicesImpl implements SudokoService {

    @Autowired
    private Solver solver;

    @Autowired
    private Sequencer sequencer;

    @Autowired
    private BoardRepository sudokoBoardRepository;

    @Override
    public BasicSudokoDTO createSudokoBoard(BasicSudokoDTO sudoko) {

        Board sudokoBoard = sudokoBoardRepository.getBoard(sequencer.next());
        sudokoBoard.create(sudoko.getSudokoBoardAsString());
        boolean isValidBoard = solver.isValid(sudokoBoard.getSolutionArray());
        if (isValidBoard) {
            Boolean solutionExist = solver.solve(sudokoBoard.getSolutionArray());
            if (solutionExist) {
                sudokoBoard.setHavingSolution(solutionExist);
                sudoko.setIsValidBoard(isValidBoard);
                sudokoBoard.setChached(Boolean.TRUE);
                sudoko.setId(sudokoBoard.getId());
            }
        }
        if (!sudoko.getIsValidBoard()) {
            throw new NotValidBoardException(sudoko.getSudokoBoardAsString());
        }

        return sudoko;
    }

    @Override
    public BasicSudokoDTO solveSudoko(Integer id) {

        Board sudokoBoard = getSudokoBoardById(id);
        if (sudokoBoard == null) {
            throw new SudokoBoardNotFoundException(id);
        }

        BasicSudokoDTO sudoko = new BasicSudokoDTO();
        sudoko.setSudokoBoadSolutionString(sudokoBoard.getSolutionArrayAsString());
        sudoko.setSolutionExists(sudokoBoard.getHavingSolution());
        sudoko.setIsValidBoard(Boolean.TRUE);
        return sudoko;
    }

    @Override
    public SudokoDTO successiveMoves(SudokoDTO sudoko) {

        Board sudokoBoard = getSudokoBoardById(sudoko.getId());
        if (sudokoBoard == null) {
            throw new SudokoBoardNotFoundException(sudoko.getId());
        }

        if (sudokoBoard.getHavingSolution()) {
            if (sudoko.getCellValue() == sudokoBoard.getValueInSolutionArray(sudoko.getRow(), sudoko.getColumn())) {
                sudoko.setIsValidMove(Boolean.TRUE);
                sudokoBoard.logUserMovies(sudoko.getRow(), sudoko.getColumn(), sudoko.getCellValue());
                if (sudokoBoard.isGameFinished()) {
                    sudoko.setIsSudokoFinished(Boolean.TRUE);
                }
            } else {
                sudoko.setIsValidMove(Boolean.FALSE);
            }
        }
        return sudoko;
    }

    private Board getSudokoBoardById(Integer id) {
        Board sudokoBoard = sudokoBoardRepository.getBoard(id);
        if (!sudokoBoard.getChached()) {
            return null;
        }
        return sudokoBoard;
    }
}
