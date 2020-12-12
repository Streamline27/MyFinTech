package lv.forfun.currencyconverter.api.fee.editor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lv.forfun.currencyconverter.api.Regex;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@Builder
@AllArgsConstructor
public class ExchangeFeeDeleteRequest {
    @NotNull
    @Valid
    ExchangeFeeDto exchangeFee;
}
