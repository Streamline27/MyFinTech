package lv.forfun.currencyconverter.api.exceptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDto {
    String objectName;
    String fieldName;
    String message;
}
