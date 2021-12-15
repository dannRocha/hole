package com.hole.services;

import java.util.List;

import com.hole.entities.RegistroBuraco;
import com.hole.exceptions.EntityNotFoundException;
import com.hole.repositories.RegistroBuracoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegistroBuracoService {
  
  private final RegistroBuracoRepository repository;
  private final CidadeService cidadeService;
  
  public RegistroBuracoService(RegistroBuracoRepository repository, CidadeService cidadeService) {
    this.repository = repository;
    this.cidadeService = cidadeService;
  }

  public List<RegistroBuraco> listarRegistrosBuracos() {
    return repository.findAll();
  }

  public RegistroBuraco registrarBuraco(RegistroBuraco buraco) {
    var cidade = cidadeService.buscarCidadePorId(buraco.getCidade().getId());
    buraco.setCidade(cidade);
    return repository.save(buraco);
  }

  public Page<RegistroBuraco> listarRegistrosBuracos(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public RegistroBuraco buscarRegistroPorId(Long id) {
    return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Recurso nãp encontrado"));
  }

  public RegistroBuraco atualizarRegistro(Long id, RegistroBuraco novoRegistro) {
    var registro = buscarRegistroPorId(id);
    novoRegistro.setId(registro.getId());
  
    return  repository.save(novoRegistro);
  }

  public RegistroBuraco removerRegistro(Long id) {
    var registro = buscarRegistroPorId(id);
    repository.deleteById(id);
    return registro;
  }

  public RegistroBuraco removerRegistroBuraco(Long id) {
    return null;
  }

}
