package lv.forfun.currencyconverter.api.fee.editor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class ExchangeFeesResponse {
    Set<ExchangeFeeDto> exchangeFees;
}
