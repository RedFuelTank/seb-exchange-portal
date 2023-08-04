package com.seb.exchangerates.currency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ExchangeRateForm(@JsonProperty(namespace = "time")
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                               LocalDate time,

                               @JsonProperty(namespace = "fromCurrency")
                               Currency fromCurrency,
                               @JsonProperty(namespace = "toCurrency")
                               Currency toCurrency,
                               @JsonProperty(namespace = "rate")
                               Double rate) {}
