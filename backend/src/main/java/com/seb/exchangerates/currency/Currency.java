package com.seb.exchangerates.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public final class Currency {
  @JsonProperty(namespace = "type")
  private final String name;

  @JsonProperty(namespace = "value")
  private final Double value;
}
