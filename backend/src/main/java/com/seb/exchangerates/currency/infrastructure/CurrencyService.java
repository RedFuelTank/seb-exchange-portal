package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.ExchangeRateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CurrencyService {
  private ExchangeRateRepository repository;
  public Iterable<ExchangeRateForm> getCurrentExchangeRates() {
    return repository.findAll();
  }
}
