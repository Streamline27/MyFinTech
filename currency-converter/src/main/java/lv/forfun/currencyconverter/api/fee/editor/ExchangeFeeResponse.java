package lv.forfun.currencyconverter.api.fee.editor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ExchangeFeeResponse {
    ExchangeFeeDto exchangeFee;
}
