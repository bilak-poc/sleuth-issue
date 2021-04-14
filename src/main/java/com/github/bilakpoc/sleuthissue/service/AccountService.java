package com.github.bilakpoc.sleuthissue.service;

import java.util.Optional;
import java.util.UUID;

import com.github.bilakpoc.sleuthissue.service.dto.AccountDto;

public interface AccountService {
  
  UUID createAccount(String accountIdentifier);
  
  Optional<AccountDto> getAccount(String accountIdentifier);
}
