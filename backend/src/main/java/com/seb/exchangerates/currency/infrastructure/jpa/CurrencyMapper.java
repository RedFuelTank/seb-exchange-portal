package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface CurrencyMapper {

  ExchangeRateStatisticsEntity toStatisticsEntity(ExchangeRateEntity entity);
  ExchangeRateForm toModel(ExchangeRateStatisticsEntity entity);

  CurrencyEntity toEntity(Currency currency);

  @Mapping(target = "id", ignore = true)
  ExchangeRateEntity toEntity(ExchangeRateForm form);

  ExchangeRateForm toModel(ExchangeRateEntity entity);
}
