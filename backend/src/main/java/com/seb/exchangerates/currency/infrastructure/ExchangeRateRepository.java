package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;

import java.util.Optional;

public interface ExchangeRateRepository {
  Iterable<ExchangeRateForm> findAll();

  boolean backup(ExchangeRateForm e);

  boolean update(ExchangeRateForm e);

  void save(ExchangeRateForm e);

  Iterable<ExchangeRateForm> getExchangeRateHistory(Currency.Type from, Currency.Type to);

  Optional<ExchangeRateForm> getCurrentExchangeRateFor(Currency.Type from, Currency.Type to);

  Iterable<Currency.Type> getCodes();
}
