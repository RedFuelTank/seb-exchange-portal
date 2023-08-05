package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.ExchangeRateForm;

public interface ExchangeRateRepository {
  Iterable<ExchangeRateForm> findAll();

  boolean backup(ExchangeRateForm e);

  boolean update(ExchangeRateForm e);

  void save(ExchangeRateForm e);
}
