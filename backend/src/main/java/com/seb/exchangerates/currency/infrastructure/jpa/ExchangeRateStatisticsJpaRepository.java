package com.seb.exchangerates.currency.infrastructure.jpa;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateStatisticsJpaRepository extends
  CrudRepository<ExchangeRateStatisticsEntity, Long>,
  QuerydslPredicateExecutor<ExchangeRateStatisticsEntity> {}
