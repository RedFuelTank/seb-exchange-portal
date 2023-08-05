package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import org.springframework.data.repository.CrudRepository;

interface CurrencyJpaRepository extends CrudRepository<CurrencyEntity, Currency.Type> { }
