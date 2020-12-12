package lv.forfun.currencyconverter.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    String message;
    Set<ErrorDto> errors;

    public static ExceptionResponse with(String message) {
        return ExceptionResponse.builder()
                .message(message)
                .build();
    }
}
