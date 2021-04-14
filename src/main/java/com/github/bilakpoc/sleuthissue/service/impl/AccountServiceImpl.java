package com.github.bilakpoc.sleuthissue.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.bilakpoc.sleuthissue.config.AccountIdKeyResolver;
import com.github.bilakpoc.sleuthissue.config.RemoteServiceProperties;
import com.github.bilakpoc.sleuthissue.service.AccountService;
import com.github.bilakpoc.sleuthissue.service.dto.AccountDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  
  private final RestTemplate restTemplate;
  private final RemoteServiceProperties properties;
  
  @NewSpan
  @Override
  public UUID createAccount(
    @SpanTag(key = "account-id", resolver = AccountIdKeyResolver.class) String accountIdentifier) {
    restTemplate.put(properties.getBaseUrl(), null);
    return UUID.randomUUID();
  }
  
  @NewSpan
  @Override
  public Optional<AccountDto> getAccount(
    @SpanTag(key = "account-id", resolver = AccountIdKeyResolver.class) String accountIdentifier) {
    restTemplate.getForEntity(properties.getBaseUrl(), String.class);
    return Optional.empty();
  }
}
