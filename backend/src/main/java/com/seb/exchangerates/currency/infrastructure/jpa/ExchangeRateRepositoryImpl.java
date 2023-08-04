package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.ExchangeRateForm;
import com.seb.exchangerates.currency.infrastructure.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
  private final ExchangeRateJpaRepository repository;
  private final CurrencyMapper mapper;

  @Override
  public Iterable<ExchangeRateForm> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), true)
      .map(mapper::toForm)
      .toList();
  }

  @Override
  public void save(Iterable<ExchangeRateForm> forms) {
    forms.forEach(e -> repository.save(mapper.toEntity(e)));
  }
}
