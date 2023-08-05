package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyJpaRepository extends CrudRepository<CurrencyEntity, Currency.Type> {

}
