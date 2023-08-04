package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "currencies")
class CurrencyEntity {
  @Id
  @Enumerated(value = EnumType.STRING)
  private Currency.Type code;

}
