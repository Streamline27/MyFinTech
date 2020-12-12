package lv.forfun.currencyconverter.api.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class CurrencyListResponse {
    Set<String> currencies;
}
