package com.seb.exchangerates.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/currency")
class CurrencyController {
  private final CurrencyService service;
  @GetMapping(value = "/current")
  Iterable<CurrencyReportForm> getCurrentExchangeRates() {
    return service.getCurrentExchangeRates();
  }
}
