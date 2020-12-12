package lv.forfun.currencyconverter.feature;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.forfun.currencyconverter.api.fee.editor.*;
import lv.forfun.currencyconverter.domain.ExchangeFee;
import lv.forfun.currencyconverter.domain.ExchangeFeeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeeEditorService {

    private final ExchangeFeeRepository repository;

    public ExchangeFeesResponse getExchangeFees() {
        log.info("Requesting list of exchange fees.");
        Set<ExchangeFeeDto> fees = repository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toSet());
        return new ExchangeFeesResponse(fees);
    }

    public ExchangeFeeResponse upsert(ExchangeFeePutRequest request) {
        ExchangeFeeDto feeDto = request.getExchangeFee();
        Optional<ExchangeFee> fee = repository.findByFromAndTo(feeDto.getFrom(), feeDto.getTo());
        if (fee.isPresent()) {
            log.info("Saving exchange fee. from:[{}], to:[{}], fee:[{}]", feeDto.getFrom(), feeDto.getTo(), feeDto.getFee());
            repository.save(fee.get().setFee(feeDto.getFee()));
        } else {
            log.info("Updating exchange fee. from:[{}], to:[{}], fee:[{}]", feeDto.getFrom(), feeDto.getTo(), feeDto.getFee());
            repository.save(map(feeDto));
        }
        return new ExchangeFeeResponse(request.getExchangeFee());
    }

    public ExchangeFeeResponse delete(ExchangeFeeDeleteRequest request) {
        ExchangeFeeDto feeDto = request.getExchangeFee();
        log.info("Deleting exchange fee. from:[{}], to:[{}]", feeDto.getFrom(), feeDto.getTo());
        Optional<ExchangeFee> fee = repository.findByFromAndTo(feeDto.getFrom(), feeDto.getTo());
        if (fee.isEmpty()) {
            throw new IllegalStateException("Could not find fee. fromCurrency:[" + feeDto.getFrom() + "], toCurrency:[" + feeDto.getTo() + "]");
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
