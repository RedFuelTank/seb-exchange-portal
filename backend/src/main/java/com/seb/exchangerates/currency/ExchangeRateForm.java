package com.seb.exchangerates.currency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ExchangeRateForm(@JsonProperty(value = "time")
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                               LocalDate time,
                               @JsonProperty(value = "from")
                               Currency fromCurrency,
                               @JsonProperty(value = "to")
                               Currency toCurrency,
                               @JsonProperty(value = "rate")
                               Double rate) {}
