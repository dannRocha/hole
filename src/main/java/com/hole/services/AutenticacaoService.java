package com.hole.services;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hole.dto.util.AuthDTO;
import com.hole.dto.util.RegistroAuthDTO;
import com.hole.dto.util.TokenDTO;
import com.hole.entities.Perfil;
import com.hole.entities.Usuario;
import com.hole.exceptions.PasswordRegisterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {
  

  @Value("${hole.jwt.expiration}")
  private String expiration;

  @Value("${hole.jwt.secret}")
  private String secret;

  @Value("${hole.jwt.issuer}")
  private String issuer;

  @Autowired
  private final AuthenticationManager authManager;
  private final UsuarioService usuarioService;

 
  public AutenticacaoService(AuthenticationManager authManager, UsuarioService usuarioService) {
    this.authManager = authManager;
    this.usuarioService = usuarioService;
  }

  public TokenDTO autenticar(AuthDTO authDTO) throws AuthenticationException {
    var auth = authManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authDTO.getEmail(), 
        authDTO.getSenha()
      )
    );

    return new TokenDTO(gerarToken(auth));

  }

  private Algorithm criarAlgorithm() {
    return Algorithm.HMAC256(secret);
  }

  private String gerarToken(Authentication auth) {
    var usuario = ( Usuario ) auth.getPrincipal();
    var hoje = new Date();
    var dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
    
    return JWT
      .create()
      .withIssuer(issuer)
      .withExpiresAt(dataExpiracao)
      .withSubject(usuario.getId().toString())
      .sign(criarAlgorithm());
  }

  public Boolean verificaToken(TokenDTO tokenDTO) {
    if(tokenDTO.getToken() == null)
      return false;

      try {
        JWT.require(criarAlgorithm())
          .withIssuer(issuer)
          .build().verify(tokenDTO.getToken());
        return  true;
      }
      catch(Exception e) {
        return false;
      }
  }

  public Long retornaIdUsuario(TokenDTO tokenDTO) {
    var subject = JWT.require(criarAlgorithm())
      .withIssuer(issuer)
      .build().verify(tokenDTO.getToken())
      .getSubject();

    return Long.parseLong(subject);
  }

  public TokenDTO inscrever(RegistroAuthDTO authDTO) {

    if(!authDTO.isValidPassword()) {
      throw new PasswordRegisterException();
    }
    
    var usuario = new Usuario(
      null, authDTO.getEmail(), 
      authDTO.getSenha(),
      new Perfil(authDTO.getPerfilId())
    );

    usuarioService.salvarUsuario(usuario);

    return autenticar(new AuthDTO(authDTO.getEmail(), authDTO.getSenha()));
  }
 
}
