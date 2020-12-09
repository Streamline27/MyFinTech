package lv.forfun.currencyconverter.api.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CurrencyDto {
    String code;
}
