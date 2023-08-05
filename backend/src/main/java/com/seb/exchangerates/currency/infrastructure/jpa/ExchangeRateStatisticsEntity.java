package com.seb.exchangerates.currency.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
class ExchangeRateStatisticsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate time;

  @ManyToOne
  private CurrencyEntity fromCurrency;

  @ManyToOne
  private CurrencyEntity toCurrency;
  private double rate;
}
