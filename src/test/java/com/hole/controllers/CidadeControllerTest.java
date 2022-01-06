package com.hole.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hole.databuilder.AuthDataBuilder;
import com.hole.databuilder.RegistroCidadeDataBuilder;
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
public class CidadeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  private AutenticacaoService auth;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void deveRequisitarListaDeCidades() throws Exception {
    var authotization = auth
      .autenticar(AuthDataBuilder.aAuth().withValidAuth().build());

    mockMvc.perform(get("/v1/cidades")
      .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authotization))
    )
    .andExpect(status().isOk());
  }
  
  @Test
  public void deveNegarRequisitacaoParaListaDeCidadesParaTokenInvalido() throws Exception {
   
    mockMvc.perform(get("/v1/cidades")
      .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", ""))
    )
    .andExpect(status().isForbidden());
  }

  @Test
  public void deveNegarCriacaoDeRegistroDeCidadeAPartirDeUsuarioComum() throws Exception {
    var authotization = auth
      .autenticar(AuthDataBuilder.aAuth().withValidAuthFromCommonUser().build());

    mockMvc.perform(post("/v1/cidades")
      .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authotization))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(
        objectMapper.writeValueAsString(
          RegistroCidadeDataBuilder.aCity().withValidContent().build()
        )
      )
    )
    .andExpect(status().isForbidden());
  }

  @Test
  public void deveNegarRemocaoDeRegistroDeCidadeAPartirDeUsuarioComum() throws Exception {
    var authotization = auth
      .autenticar(AuthDataBuilder.aAuth().withValidAuthFromCommonUser().build());

    mockMvc
      .perform(
        delete("/v1/cidades/1").header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authotization)))
      .andExpect(status().isForbidden());
  }

  @Test
  public void deveRetornaRecursoNaoEncontradoAoRemoverRegistroDeCidade() throws Exception {
    var authotization = auth
      .autenticar(AuthDataBuilder.aAuth().withValidAuth().build());

    mockMvc
      .perform(
        delete("/v1/cidades/1111111111111").header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authotization)))
      .andExpect(status().isNotFound());
  }
  
  @Test
  public void deveRetornaRecursoNaoEncontradoAoAtualizarRegistroDeCidade() throws Exception {
    var authotization = auth
      .autenticar(AuthDataBuilder.aAuth().withValidAuth().build());

    mockMvc
      .perform(
        put("/v1/cidades/1111111111111")
          .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", authotization))
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .content(
            objectMapper.writeValueAsString(
              RegistroCidadeDataBuilder.aCity().withValidContent().build()
            )
          )
      )
      .andExpect(status().isNotFound());
  }

}
