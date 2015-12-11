package com.sudokodemo.config.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sudokodemo.config.constraints.SudokuBoardAsString;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author SARAT
 */
@JsonInclude(Include.NON_NULL)
public class SudokoDTO implements Serializable {

    private Integer id;

    @NotNull
    @Min(0)
    @Max(8)
    private Integer row;
    @NotNull
    @Min(0)
    @Max(8)
    private Integer column;
    private Boolean isValidMove;
    @NotNull()
    @Min(1)
    @Max(9)
    private Integer cellValue;
    private Boolean isSudokoFinished = Boolean.FALSE;

    public SudokoDTO() {
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Boolean getIsValidMove() {
        return isValidMove;
    }

    public void setIsValidMove(Boolean isValidMove) {
        this.isValidMove = isValidMove;
    }

    public Integer getCellValue() {
        return cellValue;
    }

    public void setCellValue(Integer cellValue) {
        this.cellValue = cellValue;
    }

    public Boolean getIsSudokoFinished() {
        return isSudokoFinished;
    }

    public void setIsSudokoFinished(Boolean isSudokoFinished) {
        this.isSudokoFinished = isSudokoFinished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
