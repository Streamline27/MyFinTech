package lv.forfun.currencyconverter.api.fee.editor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lv.forfun.currencyconverter.api.Regex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class ExchangeFeeDto {
    @NotNull
    @Pattern(regexp = Regex.ISO_CURRENCY)
    String from;

    @NotNull
    @Pattern(regexp = Regex.ISO_CURRENCY)
    String to;

    @NotNull
    @PositiveOrZero
    BigDecimal fee;
}
