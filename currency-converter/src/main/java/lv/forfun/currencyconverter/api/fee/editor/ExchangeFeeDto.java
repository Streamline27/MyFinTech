package lv.forfun.currencyconverter.api.fee.editor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class ExchangeFeeDto {
    String from;
    String to;
    BigDecimal fee;
}
