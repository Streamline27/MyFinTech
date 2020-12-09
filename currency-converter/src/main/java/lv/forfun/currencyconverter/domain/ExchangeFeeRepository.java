package lv.forfun.currencyconverter.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExchangeFeeRepository extends CrudRepository<ExchangeFee, Long> {

    Optional<ExchangeFee> findByFromAndTo(String from, String to);

    List<ExchangeFee> findAll();
}
