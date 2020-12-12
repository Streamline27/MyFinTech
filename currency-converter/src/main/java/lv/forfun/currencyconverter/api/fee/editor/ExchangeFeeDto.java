package lv.forfun.currencyconverter.api.fee.editor;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "RUB")
    String from;

    @NotNull
    @Pattern(regexp = Regex.ISO_CURRENCY)
    @Schema(example = "EUR")
    String to;

    @NotNull
    @PositiveOrZero
    @Schema(example = "0.025")
    BigDecimal fee;
}
