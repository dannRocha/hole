package com.hole.services;

import java.util.List;

import com.hole.entities.Cidade;
import com.hole.exceptions.EntityNotFoundException;
import com.hole.repositories.CidadeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

  private final CidadeRepository cidadeRepository;
  private final EstadoService estadoService;


  public CidadeService(CidadeRepository cidadeRepository, EstadoService estadoService) {
    this.cidadeRepository = cidadeRepository;
    this.estadoService = estadoService;
  }

  public List<Cidade> listarCidade() {
    return cidadeRepository.findAll();
  }

  public Cidade salvarCidade(Cidade cidade) {
    var estado = estadoService.buscarEstadoPorId(cidade.getEstado().getId());
    cidade.setEstado(estado);
    return cidadeRepository.save(cidade);
  }

  public Cidade buscarCidadePorId(Long id) {
    return cidadeRepository.findById(id).orElseThrow(
      () -> new EntityNotFoundException("Recuso não encontrado")
    );
  }

  public Cidade atualizarCidade(Long id, Cidade cidade) {
    var cidadeSalvo = buscarCidadePorId(id);
    cidade.setId(cidadeSalvo.getId());
    return salvarCidade(cidade);
  }

  public Page<Cidade> listarCidade(Pageable pageable) {
    return cidadeRepository.findAll(pageable);
  }

  public Cidade removerCidade(Long id) {
    var cidadeSalvo = buscarCidadePorId(id);
    cidadeRepository.deleteById(id);
    return cidadeSalvo;
  }
}
