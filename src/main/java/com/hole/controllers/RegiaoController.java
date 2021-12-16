package com.hole.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.hole.dto.regiao.ConsultaRegiaoDTO;
import com.hole.dto.regiao.RegiaoDTO;
import com.hole.dto.regiao.RegistroRegiaoDTO;
import com.hole.mappers.RegiaoMapper;
import com.hole.services.RegiaoService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("v1/regioes")
@Api(tags="Regiao")
public class RegiaoController {
  
  private final RegiaoService regiaoService;

  public RegiaoController(RegiaoService regiaoService) {
    this.regiaoService = regiaoService;
  }

  @GetMapping
  public ResponseEntity<List<RegiaoDTO>> listarRegiao() {
    
    return ResponseEntity.ok(
      regiaoService.listarRegioes()
        .stream()
        .map(RegiaoMapper::fromEntity)
        .collect(Collectors.toList())
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<ConsultaRegiaoDTO> buscarRegiao(@PathVariable Long id) {
    return ResponseEntity.ok(
      RegiaoMapper.fromEntityConsulta(
        regiaoService.buscarRegiaoPorId(id))
    );
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<RegiaoDTO> salvarRegiao(@Valid @RequestBody RegistroRegiaoDTO regiaoDTO) {
    return ResponseEntity.ok(
      RegiaoMapper.fromEntity(
        regiaoService.salvarRegiao(RegiaoMapper.fromDTO(regiaoDTO))
      )
    );
  }
}
