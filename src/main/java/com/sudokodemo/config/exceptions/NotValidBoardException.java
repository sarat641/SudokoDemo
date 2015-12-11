/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author SARAT
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,
        reason = "SudokoBoard is not valid")
public class NotValidBoardException extends RuntimeException{
    private final String boardAsString;

    public NotValidBoardException(String boardAsString) {
        this.boardAsString = boardAsString;
    }

    public String getBoardAsString() {
        return boardAsString;
    }
}
