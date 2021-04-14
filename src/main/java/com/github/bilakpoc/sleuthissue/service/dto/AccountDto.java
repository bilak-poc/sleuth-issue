package com.github.bilakpoc.sleuthissue.service.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
  
  private UUID accountId;
  private String accountIdentifier;
  
}
