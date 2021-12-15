package com.hole.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.hole.dto.estado.DetailsEstadoDTO;
import com.hole.dto.estado.EstadoDTO;
import com.hole.dto.estado.RegistroEstadoDTO;
import com.hole.mappers.EstadoMapper;
import com.hole.services.EstadoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("v1/estados")
@Api(tags="Estado")
public class EstadoController {
  
  private final EstadoService estadoService;

  public EstadoController(EstadoService estadoService) {
    this.estadoService = estadoService;
  }

  @GetMapping
  public ResponseEntity<List<EstadoDTO>> listarEstados() {
    return ResponseEntity.ok(
      estadoService.listarEstados()
        .stream()
        .map(EstadoMapper::fromEntity)
        .collect(Collectors.toList())
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<DetailsEstadoDTO> buscarEstado(@PathVariable Long id) {
    return ResponseEntity.ok(
      EstadoMapper.fromDetailsEntity(
        estadoService.buscarEstadoPorId(id)
      )
    );
  }

  @PostMapping
  public ResponseEntity<EstadoDTO> salvarEstado(@Valid @RequestBody RegistroEstadoDTO registroDTO) {
    return ResponseEntity.ok(
      EstadoMapper.fromEntity(
        estadoService.salvarEstado(EstadoMapper.fromDTO(registroDTO))
      )
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<DetailsEstadoDTO> atualizarEstado(@PathVariable Long id, @Valid @RequestBody RegistroEstadoDTO registroDTO) {
    return ResponseEntity.ok(
      EstadoMapper.fromDetailsEntity(
        estadoService.atualizarEstado(id, EstadoMapper.fromDTO(registroDTO))
      )
    );
  }
}
