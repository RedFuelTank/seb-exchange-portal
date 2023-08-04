package com.seb.exchangerates.currency.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "exchange_rates")
class ExchangeRateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  LocalDate time;

  @OneToOne
  private CurrencyEntity fromCurrency;

  @OneToOne
  private CurrencyEntity toCurrency;

  private Double rate;

}
