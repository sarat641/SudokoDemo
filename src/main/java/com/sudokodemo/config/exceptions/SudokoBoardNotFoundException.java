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
@ResponseStatus(value = HttpStatus.NOT_FOUND,
        reason = "SudokoBoard Not Found")
public class SudokoBoardNotFoundException extends RuntimeException {

    private final Integer id;

    public SudokoBoardNotFoundException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
