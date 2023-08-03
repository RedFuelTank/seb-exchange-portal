package com.seb.exchangerates.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CurrencyService {
  private final CurrencyDataFetcher fetcher;

  public Iterable<CurrencyReportForm> getCurrentExchangeRates() {
    return fetcher.getCurrentExchangeRates();
  }
}
