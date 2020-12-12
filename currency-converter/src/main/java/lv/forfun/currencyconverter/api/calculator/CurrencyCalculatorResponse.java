package lv.forfun.currencyconverter.api.calculator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CurrencyCalculatorResponse {
    @Schema(example = "200.50")
    double conversion;
}
