package lv.forfun.currencyconverter.feature;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lv.forfun.currencyconverter.CurrencyConverterProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExchangeRatesApi {

    private final CurrencyConverterProperties properties;
    private final RestTemplate restTemplate;

    public Response getExchangeRates() {
        return getExchangeRates(latest());
    }

    public Response getExchangeRates(String from, String to) {
        return getExchangeRates(latest(from, to));
    }

    public Response getExchangeRates(String params) {
        ResponseEntity<Response> response = restTemplate.getForEntity(properties.getExchangeRateUrl() + params, Response.class);
        HttpStatus code = response.getStatusCode();
        if (code != HttpStatus.OK) {
            throw new IllegalStateException("Could not fetch data from exchange rate service! Bad response code:[" + code + "]");
        }
        return response.getBody();
    }

    private String latest() {
        return "/latest";
    }

    private String latest(String from, String to) {
        return "/latest?base=" + from + "&symbols=" + to;
    }

    @Value
    @Builder
    public static class Response {
        Map<String, Double> rates;
        String base;
    }
}
