package com.github.bilakpoc.sleuthissue.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bilakpoc.sleuthissue.controller.dto.CreateAccountBody;
import com.github.bilakpoc.sleuthissue.service.AccountService;
import com.github.bilakpoc.sleuthissue.service.dto.AccountDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {
  
  private final AccountService accountService;
  
  @PostMapping
  public ResponseEntity<UUID> createAccount(@RequestBody CreateAccountBody request) {
    final UUID accountId =  accountService.getAccount(request.getAccountIdentifier())
      .map(AccountDto::getAccountId)
      .orElseGet(() -> accountService.createAccount(request.getAccountIdentifier()));
    return ResponseEntity.ok(accountId);
  }
}
