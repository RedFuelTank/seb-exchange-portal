package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {
  private final ExchangeRateRepository exchangeRateRepository;
  public Iterable<ExchangeRateForm> getCurrentExchangeRates() {
    return exchangeRateRepository.findAll();
  }

  public void updateCurrentExchangeRates(Iterable<ExchangeRateForm> forms) {
    forms.forEach(e -> {
      exchangeRateRepository.backup(e);
      if (!exchangeRateRepository.update(e)) {
        exchangeRateRepository.save(e);
      }
    });
  }

  public Iterable<ExchangeRateForm> getExchangeRateHistory(Currency.Type from, Currency.Type to) {
    return exchangeRateRepository.getExchangeRateHistory(from ,to);
  }

  public ExchangeRateForm getCurrentExchangeRateFor(Currency.Type from, Currency.Type to) {
    return exchangeRateRepository.getCurrentExchangeRateFor(from, to)
      .orElseThrow(IllegalArgumentException::new);
  }

  public Iterable<Currency.Type> getCodes() {
    return exchangeRateRepository.getCodes();
  }
}
