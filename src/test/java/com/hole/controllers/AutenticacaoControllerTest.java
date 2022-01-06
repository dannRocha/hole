package com.hole.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hole.databuilder.AuthDataBuilder;
import com.hole.databuilder.RegisterAuthDataBuilder;
import com.hole.services.AutenticacaoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AutenticacaoControllerTest {
  
  @Autowired
  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private AutenticacaoService auth;

  @Test
  public void deveAutenticarNaAPI() throws Exception {
    mockMvc
      .perform(
        post("/v1/auth/signIn")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(
            objectMapper.writeValueAsString(
              AuthDataBuilder.aAuth().withValidAuth().build()
            )
          )
        )
      .andExpect(status().isOk());
  }

  @Test
  public void deveNegarAutenticacaoNaAPI() throws Exception {
    mockMvc
      .perform(
        post("/v1/auth/signIn")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(
            objectMapper.writeValueAsString(
              AuthDataBuilder.aAuth().withInValidAuth().build()
            )
          )
        )
      .andExpect(status().isUnauthorized());
  }

  @Test
  public void deveRetornaBadRequestAoInserirDadosInvalidosNaAutenticacaoDaAPI() throws Exception {
    mockMvc
      .perform(
        post("/v1/auth/signIn")
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(
            objectMapper.writeValueAsString(
              AuthDataBuilder.aAuth().withInInValidAuthAndBadFormat().build()
            )
          )
        )
      .andExpect(status().isBadRequest());
  }

  @Test
  public void deveNegarRegistroDeNovoUsuarioSemTokenDeAutorizacaoDeAdmin() throws Exception {
    mockMvc.perform(
      post("/v1/auth/signUp")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
        .content(
          objectMapper.writeValueAsString(
            RegisterAuthDataBuilder.aRegister().withValidRegisterAuth().build()
          )
        )
    )
    .andExpect(status().isForbidden());
  }

  @Test
  public void deveNegarRegistroDeNovoUsuarioComDadosDeRegistroInvalidos() throws Exception {
    
    var authotization = auth
      .autenticar(AuthDataBuilder.aAuth().withValidAuth().build());
    
    mockMvc.perform(
      post("/v1/auth/signUp")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authotization))
        .content(
          objectMapper.writeValueAsString(
            RegisterAuthDataBuilder.aRegister().withInValidRegisterAuth().build()
          )
        )
    )
    .andExpect(status().isBadRequest());
  }
}
