package com.hole.controllers;

import javax.validation.Valid;

import com.hole.dto.cidade.CidadeDTO;
import com.hole.dto.cidade.CidadeDetailsDTO;
import com.hole.dto.cidade.RegistroCidadeDTO;
import com.hole.mappers.CidadeMapper;
import com.hole.services.CidadeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("v1/cidades")
@Api(tags="Cidade")
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
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<CidadeDTO> salvarCidade(@Valid @RequestBody RegistroCidadeDTO cidadeDTO) {
    return ResponseEntity.ok(
      CidadeMapper.fromEntity(
        cidadeService.salvarCidade(CidadeMapper.fromDTO(cidadeDTO))
      )
    );
  }

  @PutMapping("{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<CidadeDTO> atualizarCidade(@PathVariable Long id,  @Valid @RequestBody RegistroCidadeDTO cidadeDTO) {
    return ResponseEntity.ok(
      CidadeMapper.fromEntity(
        cidadeService.atualizarCidade(id, CidadeMapper.fromDTO(cidadeDTO))
      )
    );
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<CidadeDTO> removerCidade(@PathVariable Long id) {
    return ResponseEntity.ok(
      CidadeMapper.fromEntity(cidadeService.removerCidade(id))
    );
  }

}
