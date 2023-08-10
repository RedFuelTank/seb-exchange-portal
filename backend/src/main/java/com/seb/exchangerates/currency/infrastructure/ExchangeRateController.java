package com.seb.exchangerates.currency.infrastructure;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/exchange-rates")
class ExchangeRateController {
  private final CurrencyService service;
  @GetMapping("/current/all")
  public Iterable<ExchangeRateForm> getCurrentExchangeRates() {
    return service.getCurrentExchangeRates();
  }

  @GetMapping("/current")
  public ExchangeRateForm getCurrentExchangeRateFor(@RequestParam Currency.Type from,
                                                     @RequestParam Currency.Type to) {
    return service.getCurrentExchangeRateFor(from, to);
  }

  @GetMapping("/history")
  public Iterable<ExchangeRateForm> getCurrencyExchangeRateHistory(@RequestParam Currency.Type from,
                                                                   @RequestParam Currency.Type to) {
    return service.getExchangeRateHistory(from, to);
  }
}
