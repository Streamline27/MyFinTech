package lv.forfun.currencyconverter.api.calculator;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lv.forfun.currencyconverter.api.Regex;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Value
@Builder
@AllArgsConstructor
public class CurrencyCalculatorRequest {
    @NotNull
    @Pattern(regexp = Regex.ISO_CURRENCY)
    @Schema(example = "RUB")
    String from;

    @NotNull
    @Pattern(regexp = Regex.ISO_CURRENCY)
    @Schema(example = "EUR")
    String to;

    @PositiveOrZero
    @Schema(example = "200.87")
    double amount;
}
