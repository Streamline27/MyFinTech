package lv.forfun.currencyconverter.feature.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.forfun.currencyconverter.api.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvice {

    private final NotValidExceptionMapper exceptionMapper;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handle(Exception ex) {
        log.error("Error during request processing.", ex);
        return ExceptionResponse.with(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ExceptionResponse handle(BindException ex) {
        log.error("Request validation failed.");
        return exceptionMapper.map(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ExceptionResponse handle(MethodArgumentTypeMismatchException ex) {
        String message = "Request validation failed. Could not convert:["+ ex.getValue() +"]. Parameter name:["+ ex.getName() +"]";
        log.error(message);
        return ExceptionResponse.with(message);
    }
}
