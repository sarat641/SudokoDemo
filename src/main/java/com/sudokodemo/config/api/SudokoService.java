package com.sudokodemo.config.api;

import com.sudokodemo.config.dto.BasicSudokoDTO;
import com.sudokodemo.config.dto.SudokoDTO;

/**
 *
 * @author SARAT
 */

public interface SudokoService {

    BasicSudokoDTO solveSudoko(Integer id);

    SudokoDTO successiveMoves(SudokoDTO sudoko);

    BasicSudokoDTO createSudokoBoard(BasicSudokoDTO sudoko);
    
    
    
}
