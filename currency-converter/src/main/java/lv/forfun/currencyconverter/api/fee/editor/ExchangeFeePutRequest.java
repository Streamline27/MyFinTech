package lv.forfun.currencyconverter.api.fee.editor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor
public class ExchangeFeePutRequest {
    @NotNull
    @Valid
    ExchangeFeeDto exchangeFee;
}
