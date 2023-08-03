package com.seb.exchangerates.currency;

public interface CurrencyDataFetcher {
  Iterable<ExchangeRateForm> getCurrentExchangeRates();
}
