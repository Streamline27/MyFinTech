package lv.forfun.currencyconverter.feature.validation;

import lv.forfun.currencyconverter.api.exceptions.ErrorDto;
import lv.forfun.currencyconverter.api.exceptions.ExceptionResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotValidExceptionMapper {
    public static final String VALIDATION_EXCEPTION_MESSAGE = "Request validation failed!";

    public ExceptionResponse map(BindException ex) {
        return ExceptionResponse.builder()
                .message(VALIDATION_EXCEPTION_MESSAGE)
                .errors(mapErrors(ex))
                .build();
    }

    public Set<ErrorDto> mapErrors(BindException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    public ErrorDto map(FieldError error) {
        return ErrorDto.builder()
                .fieldName(error.getField())
                .objectName(error.getObjectName())
                .message(error.getDefaultMessage())
                .build();
    }
}
