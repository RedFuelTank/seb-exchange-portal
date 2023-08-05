package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.ExchangeRateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface CurrencyMapper {

  CurrencyEntity toEntity(Currency currency);

  ExchangeRateForm toForm(ExchangeRateEntity entity);
  @Mapping(target = "id", ignore = true)
  ExchangeRateEntity toEntity(ExchangeRateForm form);
}
