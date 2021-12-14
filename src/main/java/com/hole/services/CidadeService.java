package com.hole.services;

import java.util.List;

import com.hole.entities.Cidade;
import com.hole.repositories.CidadeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

  private final CidadeRepository cidadeRepository;

  public CidadeService(CidadeRepository cidadeRepository) {
    this.cidadeRepository = cidadeRepository;
  }

  public List<Cidade> listarCidade() {
    return cidadeRepository.findAll();
  }

  public Cidade salvarCidade(Cidade cidade) {
    return cidadeRepository.save(cidade);
  }

  public Cidade buscarCidadePorId(Long id) {
    return cidadeRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Recuso não encontrado")
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
