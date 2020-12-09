package lv.forfun.currencyconverter.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrencyCalculatorResponse {
    double conversion;
}
