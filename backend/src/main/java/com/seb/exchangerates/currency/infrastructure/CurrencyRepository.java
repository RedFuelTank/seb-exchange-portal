package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.Currency;

public interface CurrencyRepository {
  Currency save(Currency currency);

  boolean exists(Currency currency);
}
