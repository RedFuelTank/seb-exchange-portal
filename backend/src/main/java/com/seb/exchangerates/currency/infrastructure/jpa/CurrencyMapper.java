package com.seb.exchangerates.currency.infrastructure.jpa;

import com.seb.exchangerates.currency.ExchangeRateForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface CurrencyMapper {
  ExchangeRateForm toForm(ExchangeRateEntity entity);

  @Mapping(target = "id", ignore = true)
  ExchangeRateEntity toEntity(ExchangeRateForm form);
}
