package com.hole.configuration;

import com.hole.entities.Perfil;
import com.hole.entities.Usuario;
import com.hole.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopulateDatabase {
  
  @Autowired
  private UsuarioService usuarioService;


  @Bean
  public void adicionarUsuarioAdmin() {
    if(!usuarioService.isUsuarioRepositoryEmpty()) {
      return;
    }

    usuarioService.salvarUsuario(
      new Usuario(null, "admin@example.com", "H0L3_3sYR@Dª", new Perfil(1L))
    );
  }
}
