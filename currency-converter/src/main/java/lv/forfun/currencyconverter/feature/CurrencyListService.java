package lv.forfun.currencyconverter.feature;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.forfun.currencyconverter.api.currency.CurrencyListResponse;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyListService {

    private final ExchangeRatesApi exchangeRatesApi;

    public CurrencyListResponse getCurrencies() {
        log.info("Requesting list of available currencies.");
        return CurrencyListResponse.builder()
                .currencies(from(exchangeRatesApi.getExchangeRates()))
                .build();
    }

    private Set<String> from(ExchangeRatesApi.Response exchangeRatesResponse) {
        Set<String> currencyList = new HashSet<>(exchangeRatesResponse.getRates().keySet());
        currencyList.add(exchangeRatesResponse.getBase());
        return currencyList;
    }
}
