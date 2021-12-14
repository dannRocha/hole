package com.hole.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hole.dto.util.TokenDTO;
import com.hole.services.AutenticacaoService;
import com.hole.services.UsuarioService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthFilter extends OncePerRequestFilter {

  private final AutenticacaoService autenticacaoService;
  private final UsuarioService usuarioService;

  public AuthFilter(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
    this.autenticacaoService = autenticacaoService;
    this.usuarioService = usuarioService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        
        var auth = request.getHeader("Authorization");

        String token = null;

        if(auth != null && auth.startsWith("Bearer ")) {
          token = auth.substring(7, auth.length());
        }

        if(autenticacaoService.verificaToken(new TokenDTO(token))) {
          var idUsuario = autenticacaoService.retornaIdUsuario(new TokenDTO(token));
          var usuario  = usuarioService.buscarUsuarioPorId(idUsuario);
          SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())
          );
        }

        filterChain.doFilter(request,response);
    
  }
  
}
