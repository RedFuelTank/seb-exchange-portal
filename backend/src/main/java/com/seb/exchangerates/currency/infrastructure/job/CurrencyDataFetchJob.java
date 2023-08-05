package com.seb.exchangerates.currency.infrastructure.job;

import com.seb.exchangerates.currency.CurrencyDataFetcher;
import com.seb.exchangerates.currency.ExchangeRateForm;
import com.seb.exchangerates.currency.infrastructure.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@RequiredArgsConstructor
@Slf4j
@DisallowConcurrentExecution
class CurrencyDataFetchJob extends QuartzJobBean {
  private final ExchangeRateRepository repository;
  private final CurrencyDataFetcher fetcher;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    Iterable<ExchangeRateForm> currentExchangeRates = fetcher.getCurrentExchangeRates();
    repository.save(currentExchangeRates);
  }
}
