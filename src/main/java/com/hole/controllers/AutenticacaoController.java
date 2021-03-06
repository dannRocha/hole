package com.hole.controllers;

import javax.validation.Valid;

import com.hole.dto.util.AuthDTO;
import com.hole.dto.util.RegistroAuthDTO;
import com.hole.dto.util.TokenDTO;
import com.hole.services.AutenticacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/v1/auth")
@Api(tags="Auth")
public class AutenticacaoController {
  
  private final AutenticacaoService authService;

  public AutenticacaoController(AutenticacaoService authService) {
    this.authService = authService;
  }

  @PostMapping("signIn")
  public ResponseEntity<TokenDTO> signIn(@Valid @RequestBody AuthDTO authDTO) {

    try {
      return ResponseEntity.ok(authService.autenticar(authDTO));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("signUp")
  @PreAuthorize("hasAnyAuthority('ADMIN')")
  public ResponseEntity<TokenDTO> signUp(@Valid @RequestBody RegistroAuthDTO authDTO) {
    return ResponseEntity.ok(
      authService.inscrever(authDTO)
    );
  }
}
