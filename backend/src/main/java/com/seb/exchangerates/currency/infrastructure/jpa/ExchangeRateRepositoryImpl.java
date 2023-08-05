package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import com.seb.exchangerates.currency.infrastructure.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
@Slf4j
class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
  private final ExchangeRateJpaRepository exchangeRateRepository;
  private final CurrencyJpaRepository currencyRepository;
  private final CurrencyMapper mapper;

  @Override
  public Iterable<ExchangeRateForm> findAll() {
    return StreamSupport.stream(exchangeRateRepository.findAll().spliterator(), true)
      .map(mapper::toForm)
      .toList();
  }

  @Override
  public void updateCurrentExchangeRates(Iterable<ExchangeRateForm> forms) {
    forms.forEach(e -> {
      Currency fromCurrency = e.fromCurrency();
      Currency toCurrency = e.toCurrency();

      boolean fromExist = currencyRepository.existsById(fromCurrency.code());
      boolean toExist = currencyRepository.existsById(toCurrency.code());

      log.info("From {}", fromExist);
      log.info("To {}", toExist);

      if (!fromExist) currencyRepository.save(mapper.toEntity(fromCurrency));
      if (!toExist) currencyRepository.save(mapper.toEntity(toCurrency));

    });

    exchangeRateRepository.saveAll(
      StreamSupport.stream(forms.spliterator(), true)
        .map(mapper::toEntity)
        .toList()
      );
    log.info("Data saving ...");
  }

  private boolean isNotExist(Currency e) {
    return !currencyRepository.existsById(e.code());
  }
}
