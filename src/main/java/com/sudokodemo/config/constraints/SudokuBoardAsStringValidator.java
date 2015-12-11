package com.sudokodemo.config.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * validate given string contains 1-9 or dot
 * @author SARAT
 */

public class SudokuBoardAsStringValidator implements ConstraintValidator<SudokuBoardAsString, String> {

    @Override
    public void initialize(SudokuBoardAsString a) {
    }

    @Override
    public boolean isValid(String t, ConstraintValidatorContext cvc) {
        String regex = "^([1-9.]*)$";
        if (t == null || (t.matches(regex) && t.length()==81)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
