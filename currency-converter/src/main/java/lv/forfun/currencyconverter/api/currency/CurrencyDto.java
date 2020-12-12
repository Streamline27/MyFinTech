package lv.forfun.currencyconverter.api.currency;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CurrencyDto {
    @Schema(example = "RUB")
    String code;
}
