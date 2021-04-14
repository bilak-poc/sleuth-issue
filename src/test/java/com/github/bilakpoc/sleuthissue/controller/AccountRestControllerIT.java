package com.github.bilakpoc.sleuthissue.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import com.github.bilakpoc.sleuthissue.config.RemoteServiceProperties;
import com.github.bilakpoc.sleuthissue.controller.dto.CreateAccountBody;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AccountRestControllerIT {
  
  @Autowired
  private WebTestClient webTestClient;
  
  @Autowired
  private RemoteServiceProperties properties;
  
  @Autowired
  private RestTemplate restTemplate;
  
  private MockRestServiceServer mockServer;
  
  @BeforeEach
  void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }
  
  @Test
  void testCreateAccount() {
    final String accountIdentifier = "someAccount";
    
    mockServer
      .expect(requestTo(properties.getBaseUrl()))
      .andExpect(method(HttpMethod.GET))
      .andExpect(header("x-custom-account-id", accountIdentifier))
      .andRespond(withStatus(HttpStatus.OK));
    
    mockServer
      .expect(requestTo(properties.getBaseUrl()))
      .andExpect(method(HttpMethod.PUT))
      .andExpect(header("x-custom-account-id", accountIdentifier))
      .andRespond(withStatus(HttpStatus.OK));
    
    CreateAccountBody body = new CreateAccountBody();
    body.setAccountIdentifier(accountIdentifier);
    
    final UUID accountId = webTestClient
      .post()
      .uri("/accounts")
      .body(Mono.just(body), CreateAccountBody.class)
      .exchange()
      .expectBody(UUID.class)
      .returnResult()
      .getResponseBody();
    
    assertThat(accountId, notNullValue());
  }
  
}