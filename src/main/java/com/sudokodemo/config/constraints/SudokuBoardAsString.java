/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudokodemo.config.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author SARAT
 */
@Constraint(validatedBy = {SudokuBoardAsStringValidator.class})
@Target({ElementType.METHOD,
    ElementType.FIELD,
    ElementType.CONSTRUCTOR,
    ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SudokuBoardAsString {

    String message() default "{invalid.sudokoBoardAsString}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
