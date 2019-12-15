package br.com.reclameaqui.complainsapi.shared;

import br.com.reclameaqui.complainsapi.shared.exception.NotFoundException;
import br.com.reclameaqui.complainsapi.shared.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ApiErrorDTO handleNotFound(NotFoundException e) {
        return new ApiErrorDTO(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TokenException.class})
    public ApiErrorDTO handleTokenException(TokenException e) {
        return new ApiErrorDTO(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ApiErrorDTO> handleValidation(MethodArgumentNotValidException exception) {
        List<ApiErrorDTO> dtos = new ArrayList<>();
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            ApiErrorDTO erro = new ApiErrorDTO(e.getField(), messageSource.getMessage(e, LocaleContextHolder.getLocale()));
            dtos.add(erro);
        });
        return dtos;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, NullPointerException.class, RuntimeException.class})
    public ApiErrorDTO handleNotFound(Exception e) {
        return new ApiErrorDTO(e.getClass().getSimpleName(), e.getMessage());
    }
}
