package com.hole.services;

import java.util.List;

import com.hole.entities.Estado;
import com.hole.exceptions.EntityNotFoundException;
import com.hole.repositories.EstadoRepository;

import org.springframework.stereotype.Service;

@Service
public class EstadoService {
  
  private final EstadoRepository estadoRepository;
  private final RegiaoService regiaoService;


  public EstadoService(EstadoRepository estadoRepository, RegiaoService regiaoService) {
    this.estadoRepository = estadoRepository;
    this.regiaoService = regiaoService;
  }

  public List<Estado> listarEstados() {
    return estadoRepository.findAll();
  }

  public Estado salvarEstado(Estado estado) {
    var regiao = regiaoService.buscarRegiaoPorId(estado.getRegiao().getId());
    estado.setRegiao(regiao);
    return estadoRepository.save(estado);
  }

  public Estado buscarEstadoPorId(Long id) {
    return estadoRepository.findById(id).orElseThrow(() ->
      new EntityNotFoundException("Estado não encontrado")
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
