package com.hole.controllers;

import com.hole.dto.cidade.CidadeDTO;
import com.hole.dto.cidade.CidadeDetailsDTO;
import com.hole.dto.cidade.RegistroCidadeDTO;
import com.hole.entities.Cidade;
import com.hole.mappers.CidadeMapper;
import com.hole.services.CidadeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cidades")
public class CidadeController {
  
  private final CidadeService cidadeService;

  public CidadeController(CidadeService cidadeService) {
    this.cidadeService = cidadeService;
  }

  @GetMapping
  public ResponseEntity<Page<CidadeDTO>> listarCidades(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(
      cidadeService.listarCidade(pageable)
        .map(CidadeMapper::fromEntity)
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<CidadeDetailsDTO> buscarCidades(@PathVariable Long id) {
    return ResponseEntity.ok(
      CidadeMapper.fromEntityDetails(
        cidadeService.buscarCidadePorId(id)
      )
    );
  }


  @PostMapping
  public ResponseEntity<Cidade> salvarCidade(@RequestBody RegistroCidadeDTO cidadeDTO) {
    return ResponseEntity.ok(
      cidadeService.salvarCidade(CidadeMapper.fromDTO(cidadeDTO))
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<CidadeDTO> atualizarCidade(@PathVariable Long id,  @RequestBody RegistroCidadeDTO cidadeDTO) {
    return ResponseEntity.ok(
      CidadeMapper.fromEntity(
        cidadeService.atualizarCidade(id, CidadeMapper.fromDTO(cidadeDTO))
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<CidadeDTO> removerCidade(@PathVariable Long id) {
    return ResponseEntity.ok(
      CidadeMapper.fromEntity(cidadeService.removerCidade(id))
    );
  }

}
