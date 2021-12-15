package com.hole.controllers;

import javax.validation.Valid;

import com.hole.dto.util.AuthDTO;
import com.hole.dto.util.TokenDTO;
import com.hole.services.AutenticacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth")
public class AutenticacaoController {
  
  private final AutenticacaoService authService;

  public AutenticacaoController(AutenticacaoService authService) {
    this.authService = authService;
  }

  @PostMapping
  public ResponseEntity<TokenDTO> login(@Valid @RequestBody AuthDTO authDTO) {

    try {
      return ResponseEntity.ok(authService.autenticar(authDTO));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
