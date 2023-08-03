package com.seb.exchangerates.thirdapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.seb.exchangerates.currency.Currency;
import com.seb.exchangerates.currency.CurrencyDataFetcher;
import com.seb.exchangerates.currency.ExchangeRateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiClient implements CurrencyDataFetcher {
  private static final WebClient webClient = WebClient.create("http://www.lb.lt/webservices/FxRates/FxRates.asmx");
  private final XmlMapper xmlMapper;
  @Override
  public Iterable<ExchangeRateForm> getCurrentExchangeRates() {
    log.info("Data fetching process is running ...");
    Mono<String> rawBody = get("/getCurrentFxRates?tp=EU");

    List<FxRate> body = rawBody.mapNotNull(xmlResponse -> {
      try {
        return xmlMapper.readValue(xmlResponse, new TypeReference<List<FxRate>>() {});
      } catch (Exception e) {
        log.error("Raw data cannot be cast to object by Jackson");
        return null;
      }
    }).block();
    log.info("Data has been fetched : {}", body);

    return body.stream().parallel()
      .map(ApiClient::getCurrencyReportForm)
      .toList();
  }

  private static ExchangeRateForm getCurrencyReportForm(FxRate e) {
    return new ExchangeRateForm(
      e.getDate(),
      e.getCurrencyAmounts().stream().map(c -> new Currency(c.getCurrency(), c.getAmount())).toList()
    );
  }

  private static Mono<String> get(String uri) {
    return webClient.get().uri(uri)
      .retrieve()
      .bodyToMono(String.class);
  }
}
