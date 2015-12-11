package com.sudokodemo.config.resources;

import com.sudokodemo.config.api.SudokoService;
import com.sudokodemo.config.dto.BasicSudokoDTO;
import com.sudokodemo.config.dto.SudokoDTO;
import com.sudokodemo.config.dto.ValidationErrorDTO;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SARAT
 */
@RestController
@RequestMapping("/sudoko/rest/api")
public class SudokoResource {

    private final MessageSource messageSource;
    private final SudokoService service;

    @Autowired
    public SudokoResource(MessageSource messageSource, SudokoService service) {
        this.messageSource = messageSource;
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "Hello Sudoko Application";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BasicSudokoDTO createSudokoBoard(@RequestBody @Valid BasicSudokoDTO sudoko) {
        
        return service.createSudokoBoard(sudoko);

    }

    @RequestMapping(path = "/solve/{id}", method = RequestMethod.GET)
    public BasicSudokoDTO solveSudoko(@PathVariable Integer id) {

        return service.solveSudoko(id);
    }

    @RequestMapping(path = "/successiveMoves", method = RequestMethod.POST)
    public SudokoDTO successiveMoves(@RequestBody @Valid SudokoDTO sudoko) {

        return service.successiveMoves(sudoko);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ValidationErrorDTO dto = new ValidationErrorDTO();

        fieldErrors.stream().forEach((fieldError) -> {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        });

        return dto;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}
