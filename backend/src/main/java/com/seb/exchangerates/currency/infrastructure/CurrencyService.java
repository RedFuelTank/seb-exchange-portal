package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import com.seb.exchangerates.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

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
    var exceptionMessage = String.format("Exchange rate history (from: %s, to: %s) has not been found", from, to);
    List<ExchangeRateForm> exchangeRateHistory = StreamSupport.stream(
        exchangeRateRepository.getExchangeRateHistory(from, to).spliterator(), true
      )
      .toList();
    if (exchangeRateHistory.isEmpty()) throw new NotFoundException(exceptionMessage);
    return exchangeRateHistory;
  }

  public ExchangeRateForm getCurrentExchangeRateFor(Currency.Type from, Currency.Type to) {
    var exceptionMessage = String.format("Exchange rate (from: %s, to: %s) has not been found", from, to);
    return exchangeRateRepository.getCurrentExchangeRateFor(from, to)
      .orElseThrow(() -> new NotFoundException(exceptionMessage));
  }

  public Iterable<Currency.Type> getCodes() {
    return exchangeRateRepository.getCodes();
  }
}
