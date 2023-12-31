package com.seb.exchangerates.currency.infrastructure.jpa;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

interface ExchangeRateJpaRepository extends
  CrudRepository<ExchangeRateEntity, Long>,
  QuerydslPredicateExecutor<ExchangeRateEntity> {}
