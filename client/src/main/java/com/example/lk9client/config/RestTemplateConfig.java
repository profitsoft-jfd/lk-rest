package com.example.lk9client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

  public static final Duration REST_TEMPLATE_TIMEOUT = Duration.ofSeconds(30);

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .setConnectTimeout(REST_TEMPLATE_TIMEOUT)
        .setReadTimeout(REST_TEMPLATE_TIMEOUT)
        .build();
  }

}
