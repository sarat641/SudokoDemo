package com.sudokodemo.config.exceptions;

import com.sudokodemo.config.dto.FieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author SARAT
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({org.springframework.http.converter.HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String resolveException() {
        return "Invalid JSON Format";
    }

    @ExceptionHandler(SudokoBoardNotFoundException.class)
    public ResponseEntity<FieldErrorDTO> sudokoBoardNotFound(SudokoBoardNotFoundException e) {
        Integer id = e.getId();
        FieldErrorDTO error = new FieldErrorDTO("Id", "Sudoko board " + id + " not found");
        ResponseEntity response = new ResponseEntity(error, HttpStatus.NOT_FOUND);
        return response;
    }
    @ExceptionHandler(NotValidBoardException.class)
    public ResponseEntity<FieldErrorDTO> sudokoBoardNotValid(NotValidBoardException e){
        String boardAsString = e.getBoardAsString();
        FieldErrorDTO error = new FieldErrorDTO("sudokoBoardAsString", "Sudoko board " + boardAsString + " not valid");
        ResponseEntity response = new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        return response;
    }
}
