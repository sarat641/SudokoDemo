/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.repository;

import com.sudokodemo.config.api.Board;
import com.sudokodemo.config.api.BoardRepository;
import com.sudokodemo.config.beans.SudokoBoard;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAT
 */
@Component
public class SudokoBoardRepository implements BoardRepository {

    @Override
    @Cacheable("boards")
    public Board getBoard(Integer id) {
        return new SudokoBoard(id);
    }

}
