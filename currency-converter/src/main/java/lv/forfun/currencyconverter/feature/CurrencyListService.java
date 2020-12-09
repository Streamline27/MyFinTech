package lv.forfun.currencyconverter.feature;

import lombok.RequiredArgsConstructor;
import lv.forfun.currencyconverter.api.currency.CurrencyListResponse;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CurrencyListService {

    private final ExchangeRatesApi exchangeRatesApi;

    public CurrencyListResponse getCurrencies() {
        return CurrencyListResponse.builder()
                .currencies(from(exchangeRatesApi.getExchangeRates()))
                .build();
    }

    private Set<String> from(ExchangeRatesApi.Response exchangeRatesResponse) {
        Set<String> currencyDtos = new HashSet<>(exchangeRatesResponse.getRates().keySet());
        currencyDtos.add(exchangeRatesResponse.getBase());
        return currencyDtos;
    }
}
