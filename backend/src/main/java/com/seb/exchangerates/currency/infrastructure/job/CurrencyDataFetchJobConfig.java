package com.seb.exchangerates.currency.infrastructure.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CurrencyDataFetchJobConfig {
  @Bean
  JobDetail currencyJodDetail() {
    return JobBuilder.newJob(CurrencyDataFetchJob.class)
      .storeDurably()
      .requestRecovery(true)
      .build();
  }

  @Bean
  Trigger trigger() {
    return TriggerBuilder.newTrigger()
      .forJob(currencyJodDetail())
      .withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ?"))
      .build();
  }
}
