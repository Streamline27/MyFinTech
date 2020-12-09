package lv.forfun.currencyconverter.feature;

import lombok.RequiredArgsConstructor;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeeDto;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeePutRequest;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeeResponse;
import lv.forfun.currencyconverter.api.fee.editor.ExchangeFeesResponse;
import lv.forfun.currencyconverter.domain.ExchangeFee;
import lv.forfun.currencyconverter.domain.ExchangeFeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FeeEditorService {

    private final ExchangeFeeRepository repository;

    public ExchangeFeesResponse getExchangeFees() {
        Set<ExchangeFeeDto> fees = repository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toSet());
        return new ExchangeFeesResponse(fees);
    }

    public ExchangeFeeResponse save(ExchangeFeePutRequest request) {
        ExchangeFee fee = map(request.getExchangeFee());
        repository.save(fee);
        return new ExchangeFeeResponse(request.getExchangeFee());
    }

    public ExchangeFeeResponse delete(String fromCurrencyCode, String toCurrencyCode) {
        Optional<ExchangeFee> fee = repository.findByFromAndTo(fromCurrencyCode, toCurrencyCode);
        if (fee.isEmpty()) {
            throw new IllegalStateException("Could not find fee. fromCurrency:[" + fromCurrencyCode + "], toCurrency:[" + toCurrencyCode + "]");
        }
        repository.delete(fee.get());
        return new ExchangeFeeResponse(map(fee.get()));
    }

    private ExchangeFee map(ExchangeFeeDto exchangeFee) {
        return ExchangeFee.builder()
                .from(exchangeFee.getFrom())
                .to(exchangeFee.getTo())
                .fee(exchangeFee.getFee())
                .build();
    }

    private ExchangeFeeDto map(ExchangeFee exchangeFee) {
        return ExchangeFeeDto.builder()
                .from(exchangeFee.getFrom())
                .to(exchangeFee.getTo())
                .fee(exchangeFee.getFee())
                .build();
    }
}
