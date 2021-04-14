package com.github.bilakpoc.sleuthissue.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties
public class RemoteServiceProperties {
  
  private String baseUrl = "http://localhost:10001";
}
