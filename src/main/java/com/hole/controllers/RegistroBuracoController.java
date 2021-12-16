package com.hole.controllers;

import javax.validation.Valid;

import com.hole.dto.buraco.ConsultaRegistroBuracoDTO;
import com.hole.dto.buraco.RegistroBuracoDTO;
import com.hole.entities.RegistroBuraco;
import com.hole.mappers.RegistroBuracoMapper;
import com.hole.services.RegistroBuracoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("v1/buracos")
@Api(tags="Registro de Buraco")
public class RegistroBuracoController {
  
  private final RegistroBuracoService registroBuracoService;

  public RegistroBuracoController(RegistroBuracoService registroBuracoService) {
    this.registroBuracoService = registroBuracoService;
  }

  @GetMapping
  public ResponseEntity<Page<ConsultaRegistroBuracoDTO>> listarRegistrosDeBuracos(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(
      registroBuracoService.listarRegistrosBuracos(pageable)
        .map(RegistroBuracoMapper::fromEntity)
    );
  } 


  @PostMapping
  public ResponseEntity<RegistroBuraco> registrarBuraco(@Valid @RequestBody RegistroBuracoDTO registroDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
      registroBuracoService.registrarBuraco(RegistroBuracoMapper.fromDTO(registroDTO))
    );
  }

  @PutMapping("{id}")
  public ResponseEntity<ConsultaRegistroBuracoDTO> atualizarRegistroBuraco(
    @PathVariable Long id, 
    @Valid @RequestBody RegistroBuracoDTO registroDTO) 
  {
    return ResponseEntity.ok(
      RegistroBuracoMapper.fromEntity(
        registroBuracoService.atualizarRegistro(id, RegistroBuracoMapper.fromDTO(registroDTO))
      )
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ConsultaRegistroBuracoDTO> removerRegistroBuraco(@PathVariable Long id) {
    return ResponseEntity.ok(
      RegistroBuracoMapper.fromEntity(
        registroBuracoService.removerRegistro(id)
      )
    );
  }

}
