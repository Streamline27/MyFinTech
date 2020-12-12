package lv.forfun.currencyconverter.api.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDto {
    @Schema(example = "exchangeFee")
    String objectName;

    @Schema(example = "from")
    String fieldName;

    @Schema(example = "must match '[A-Z]{1,3}'")
    String message;
}
