package com.hole.services;

import java.util.List;

import com.hole.entities.Estado;
import com.hole.repositories.EstadoRepository;

import org.springframework.stereotype.Service;

@Service
public class EstadoService {
  
  private final EstadoRepository estadoRepository;

  public EstadoService(EstadoRepository estadoRepository) {
    this.estadoRepository = estadoRepository;
  }

  public List<Estado> listarEstados() {
    return estadoRepository.findAll();
  }

  public Estado salvarEstado(Estado estado) {
    return estadoRepository.save(estado);
  }

  public Estado buscarEstadoPorId(Long id) {
    return estadoRepository.findById(id).orElseThrow(() ->
      new RuntimeException("Recuso não encontrado")
    );
  }

  public Estado atualizarEstado(Long id, Estado estado) {
    var estadoSalvo = buscarEstadoPorId(id);
    estadoSalvo.setUf(estado.getUf());
    estadoSalvo.setNome(estado.getNome());
    estadoSalvo.setRegiao(estado.getRegiao());
    return salvarEstado(estadoSalvo);
  }
}
