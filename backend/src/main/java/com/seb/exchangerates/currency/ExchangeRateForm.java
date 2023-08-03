package com.seb.exchangerates.currency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public final class ExchangeRateForm {
  @JsonProperty(namespace = "time")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private final LocalDate time;

  @JsonProperty(namespace = "exchangeRate")
  private final Iterable<Currency> currencies;
}
