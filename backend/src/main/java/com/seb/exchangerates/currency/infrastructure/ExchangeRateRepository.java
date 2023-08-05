package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.ExchangeRateForm;

public interface ExchangeRateRepository {
  Iterable<ExchangeRateForm> findAll();

  void updateCurrentExchangeRates(Iterable<ExchangeRateForm> forms);
}
