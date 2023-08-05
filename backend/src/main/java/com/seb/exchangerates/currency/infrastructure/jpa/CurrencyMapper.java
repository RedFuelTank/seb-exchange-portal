package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface CurrencyMapper {

  CurrencyEntity toEntity(Currency currency);

  Currency toModel(CurrencyEntity entity);

  @Mapping(target = "id", ignore = true)
  ExchangeRateEntity toEntity(ExchangeRateForm form);
  ExchangeRateForm toModel(ExchangeRateEntity entity);
}
