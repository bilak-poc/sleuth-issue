package com.github.bilakpoc.sleuthissue.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(RemoteServiceProperties.class)
public class RemoteServiceConfig {
  
  @Bean
  RestTemplate remoteServiceTemplate() {
    return new RestTemplate();
  }
  
}
