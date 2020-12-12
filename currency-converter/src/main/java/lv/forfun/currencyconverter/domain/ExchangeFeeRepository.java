package lv.forfun.currencyconverter.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeFeeRepository extends CrudRepository<ExchangeFee, Long> {

    Optional<ExchangeFee> findByFromAndTo(String from, String to);

    List<ExchangeFee> findAll();
}
