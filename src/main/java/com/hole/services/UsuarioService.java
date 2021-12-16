package com.hole.services;

import com.hole.entities.Usuario;
import com.hole.exceptions.EmailExistsException;
import com.hole.exceptions.EntityNotFoundException;
import com.hole.repositories.PerfilRepository;
import com.hole.repositories.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
  
  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;


  public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
    this.usuarioRepository = usuarioRepository;
    this.perfilRepository = perfilRepository;
  }

  public Usuario buscarUsuarioPorEmail(String email) {
    return usuarioRepository.findByEmail(email).orElseThrow(() ->
      new EntityNotFoundException("Usuário ou senha invalido")
    );
  }

  public Boolean emailExists(String email) {
    return usuarioRepository.findByEmail(email).isPresent();
  }

  public Usuario buscarUsuarioPorId(Long id) {
    return usuarioRepository.findById(id).orElseThrow(() ->
      new EntityNotFoundException("Recurso nao encontrado")
    );
  }

  public Usuario salvarUsuario(Usuario usuario) {

    usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
      
    var usuarioSalvo =  usuarioRepository.findByEmail(usuario.getEmail());
    var perfilSalvo = perfilRepository.findById(usuario.getPerfil().getId());

    if(perfilSalvo.isEmpty()) {
      throw new EntityNotFoundException("Perfil não encontrado");
    }

    usuario.setPerfil(perfilSalvo.get());

    if(usuarioSalvo.isPresent()) {
     throw new EmailExistsException();
    }
    
    return  usuarioRepository.save(usuario);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = buscarUsuarioPorEmail(username);
    return user;
  }

  public Boolean isUsuarioRepositoryEmpty() {
    return usuarioRepository.count() == 0;
  }
}
