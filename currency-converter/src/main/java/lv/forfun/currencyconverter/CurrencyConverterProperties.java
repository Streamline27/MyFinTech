package lv.forfun.currencyconverter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@ConfigurationProperties("fee-editor")
public class CurrencyConverterProperties {
    private String exchangeRateUrl;
    private BigDecimal defaultFee;
}
