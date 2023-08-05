package com.seb.exchangerates.currency.infrastructure;

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
}
