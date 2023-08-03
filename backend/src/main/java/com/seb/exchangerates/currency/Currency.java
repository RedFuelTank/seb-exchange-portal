package com.seb.exchangerates.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Currency(@JsonProperty(namespace = "type")
                         String name,
                       @JsonProperty(namespace = "value")
                       Double value) {
  public enum Type {
    USD,
    EUR
  }
}
