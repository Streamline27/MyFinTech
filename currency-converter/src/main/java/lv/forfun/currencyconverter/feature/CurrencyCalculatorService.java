package lv.forfun.currencyconverter.feature;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.forfun.currencyconverter.CurrencyConverterProperties;
import lv.forfun.currencyconverter.api.calculator.CurrencyCalculatorResponse;
import lv.forfun.currencyconverter.domain.ExchangeFee;
import lv.forfun.currencyconverter.domain.ExchangeFeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyCalculatorService {

    private final CurrencyConverterProperties properties;
    private final ExchangeRatesApi exchangeRatesApi;
    private final ExchangeFeeRepository exchangeFeeRepository;

    public CurrencyCalculatorResponse getConversion(String fromCurrency, String toCurrency, double fromAmount) {
        log.info("Computing amount after conversion. from:[{}], to:[{}], fromAmount:[{}]", fromCurrency, toCurrency, fromAmount);
        BigDecimal conversion = computeConversion(fromCurrency.toUpperCase().trim(), toCurrency.toUpperCase().trim(), BigDecimal.valueOf(fromAmount));
        return CurrencyCalculatorResponse.builder()
                .conversion(conversion.setScale(2, RoundingMode.HALF_DOWN).doubleValue())
                .build();
    }

    private BigDecimal computeConversion(String from, String to, BigDecimal amount) {
        BigDecimal exchangeRateMultiplier = getExchangeRate(from, to);
        BigDecimal commissionFeeMultiplier = getCommissionFee(from, to);
        BigDecimal commissionFee = amount.multiply(commissionFeeMultiplier);
        return amount.add(commissionFee.negate()).multiply(exchangeRateMultiplier);
    }

    private BigDecimal getExchangeRate(String from, String to) {
        if (from.equals(to)) return BigDecimal.ONE;
        ExchangeRatesApi.Response response = exchangeRatesApi.getExchangeRates(from, to);
        Double exchangeFee = response.getRates().entrySet().iterator().next().getValue();
        return BigDecimal.valueOf(exchangeFee);
    }

    private BigDecimal getCommissionFee(String from, String to) {
        return exchangeFeeRepository.findByFromAndTo(from, to)
                .map(ExchangeFee::getFee)
                .orElse(properties.getDefaultFee());
    }
}
