package com.seb.exchangerates.thirdapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class FxRate {

  @JacksonXmlProperty(localName = "Tp")
  private String type;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @JacksonXmlProperty(localName = "Dt")
  private LocalDate date;

  @JacksonXmlProperty(localName = "CcyAmt")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<CcyAmt> currencyAmounts;

  @Getter
  @Setter
  @RequiredArgsConstructor
  public static class CcyAmt {
    @JacksonXmlProperty(localName = "Ccy")
    private String currency;

    @JacksonXmlProperty(localName = "Amt")
    private double amount;
  }
}
