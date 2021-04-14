package com.github.bilakpoc.sleuthissue.config;

import org.springframework.cloud.sleuth.annotation.TagValueResolver;
import org.springframework.stereotype.Component;

import brave.baggage.BaggageField;

@Component
public class AccountIdKeyResolver implements TagValueResolver {
  
  @Override
  public String resolve(Object parameter) {
    final String accountId = String.valueOf(parameter);
    BaggageField.create("x-custom-account-id").updateValue(accountId);
    return accountId;
  }
}
