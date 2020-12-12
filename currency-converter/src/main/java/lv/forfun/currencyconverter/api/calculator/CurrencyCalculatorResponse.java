package lv.forfun.currencyconverter.api.calculator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CurrencyCalculatorResponse {
    double conversion;
}
