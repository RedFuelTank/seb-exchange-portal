package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.infrastructure.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CurrencyRepositoryImpl implements CurrencyRepository {
  private final CurrencyJpaRepository repository;
  private final CurrencyMapper mapper;

  @Override
  public Currency save(Currency currency) {
    return mapper.toModel(
      repository.save(mapper.toEntity(currency))
    );
  }

  @Override
  public boolean exists(Currency currency) {
    return repository.existsById(currency.code());
  }
}
