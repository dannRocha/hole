package com.hole.services;

import java.util.List;

import com.hole.entities.Regiao;
import com.hole.repositories.RegiaoRepository;

import org.springframework.stereotype.Service;

@Service
public class RegiaoService {
  

  private final RegiaoRepository regiaoRepository;

  public RegiaoService(RegiaoRepository regiaoRepository) {
    this.regiaoRepository = regiaoRepository;
  }

  public Regiao salvarRegiao(Regiao regiao) {
    return regiaoRepository.save(regiao);
  }

  public List<Regiao> listarRegioes() {
    return regiaoRepository.findAll();
  }

  public Regiao buscarRegiaoPorId(Long id) {
    return regiaoRepository.findById(id).orElseThrow();
  }
}
