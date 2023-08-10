package com.seb.exchangerates.currency.infrastructure.jpa;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

interface ExchangeRateStatisticsJpaRepository extends
  CrudRepository<ExchangeRateStatisticsEntity, Long>,
  QuerydslPredicateExecutor<ExchangeRateStatisticsEntity> { }
