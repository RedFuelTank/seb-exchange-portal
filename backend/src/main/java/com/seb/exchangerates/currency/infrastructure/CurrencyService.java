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
  private final CurrencyRepository currencyRepository;
  public Iterable<ExchangeRateForm> getCurrentExchangeRates() {
    return exchangeRateRepository.findAll();
  }

  public void updateCurrentExchangeRates(Iterable<ExchangeRateForm> forms) {
    forms.forEach(e -> {
      log.info("Data saving ...");
      Currency fromCurrency = e.fromCurrency();
      Currency toCurrency = e.toCurrency();

      boolean fromExist = currencyRepository
        .exists(fromCurrency);
      boolean toExist = currencyRepository.exists(toCurrency);

      if (!fromExist) currencyRepository.save(fromCurrency);
      if (!toExist) currencyRepository.save(toCurrency);

      exchangeRateRepository.save(e);
    });

  }
}
