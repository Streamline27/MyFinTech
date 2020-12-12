package lv.forfun.currencyconverter.api.calculator;

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
    String from;

    @NotNull
    @Pattern(regexp = Regex.ISO_CURRENCY)
    String to;

    @PositiveOrZero
    double amount;
}
