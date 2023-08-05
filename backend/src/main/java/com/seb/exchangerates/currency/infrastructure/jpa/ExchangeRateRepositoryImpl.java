package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.ExchangeRateForm;
import com.seb.exchangerates.currency.infrastructure.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
  private final ExchangeRateJpaRepository exchangeRateRepository;
  private final CurrencyJpaRepository currencyRepository;
  private final ExchangeRateStatisticsJpaRepository statisticsRepository;

  private final CurrencyMapper mapper;

  @Override
  public Iterable<ExchangeRateForm> findAll() {
    return StreamSupport.stream(exchangeRateRepository.findAll().spliterator(), true)
      .map(mapper::toModel)
      .toList();
  }

  @Override
  public boolean backup(ExchangeRateForm e) {
    QExchangeRateEntity qExchangeRate = QExchangeRateEntity.exchangeRateEntity;
    Optional<ExchangeRateEntity> possibleExchangeRate = exchangeRateRepository.findOne(
      qExchangeRate.fromCurrency.code.eq(e.fromCurrency().code())
        .and(qExchangeRate.toCurrency.code.eq(e.toCurrency().code()))
        .and(qExchangeRate.time.before(e.time()))
    );

    if (possibleExchangeRate.isPresent()) {
      ExchangeRateEntity exchangeRateEntity = possibleExchangeRate.get();
      statisticsRepository.save(mapper.toStatisticsEntity(exchangeRateEntity));
      return true;
    }
    return false;
  }

  @Override
  public boolean update(ExchangeRateForm e) {
    QExchangeRateEntity qExchangeRate = QExchangeRateEntity.exchangeRateEntity;
    Optional<ExchangeRateEntity> possibleExchangeRate = exchangeRateRepository.findOne(qExchangeRate.fromCurrency.code.eq(e.fromCurrency().code())
      .and(qExchangeRate.toCurrency.code.eq(e.toCurrency().code())));

    if (possibleExchangeRate.isPresent()) {
      ExchangeRateEntity exchangeRateEntity = possibleExchangeRate.get();
      exchangeRateEntity.setTime(e.time());
      exchangeRateEntity.setFromCurrency(mapper.toEntity(e.fromCurrency()));
      exchangeRateEntity.setToCurrency(mapper.toEntity(e.toCurrency()));
    }
    return false;
  }

  @Override
  public void save(ExchangeRateForm e) {
    if (!currencyRepository.existsById(e.fromCurrency().code()))
      currencyRepository.save(mapper.toEntity(e.fromCurrency()));
    if (!currencyRepository.existsById(e.toCurrency().code())) currencyRepository.save(mapper.toEntity(e.toCurrency()));

    exchangeRateRepository.save(mapper.toEntity(e));
  }
}
