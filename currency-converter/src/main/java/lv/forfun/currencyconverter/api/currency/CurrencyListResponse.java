package lv.forfun.currencyconverter.api.currency;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class CurrencyListResponse {
    Set<String> currencies;
}
