package lv.forfun.currencyconverter.api.fee.editor;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExchangeFeePutRequest {
    ExchangeFeeDto exchangeFee;
}
