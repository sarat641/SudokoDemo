/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sudokodemo.config.constraints.SudokuBoardAsString;
import java.io.IOException;
import javax.validation.constraints.NotNull;

/**
 *
 * @author SARAT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicSudokoDTO extends JsonSerializer<BasicSudokoDTO> {

    private Integer id;
    @NotNull
    @SudokuBoardAsString
    private String sudokoBoardAsString;
    
    private Boolean isValidBoard = Boolean.FALSE;
    protected Boolean solutionExists;
    private String sudokoBoadSolutionString;

    public BasicSudokoDTO() {
    }

    public void setIsValidBoard(Boolean isValidBoard) {
        this.isValidBoard = isValidBoard;
    }

    public Boolean getIsValidBoard() {
        return isValidBoard;
    }

    public Boolean getSolutionExists() {
        return solutionExists;
    }

    public String getSudokoBoadSolutionString() {
        return sudokoBoadSolutionString;
    }

    public void setSolutionExists(Boolean solutionExists) {
        this.solutionExists = solutionExists;
    }

    public void setSudokoBoadSolutionString(String sudokoBoadSolutionString) {
        this.sudokoBoadSolutionString = sudokoBoadSolutionString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSudokoBoardAsString() {
        return sudokoBoardAsString;
    }

    public void setSudokoBoardAsString(String sudokoBoardAsString) {
        this.sudokoBoardAsString = sudokoBoardAsString;
    }

    @Override
    public void serialize(BasicSudokoDTO t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, BasicSudokoDTO value) {
        return super.isEmpty(provider, value); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
