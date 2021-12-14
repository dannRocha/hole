package com.hole.mappers;

import java.util.stream.Collectors;

import com.hole.dto.cidade.CidadeDTO;
import com.hole.dto.cidade.CidadeDetailsDTO;
import com.hole.dto.cidade.RegistroCidadeDTO;
import com.hole.dto.cidade.SimpleCidadeDTO;
import com.hole.entities.Cidade;
import com.hole.entities.Estado;

public class CidadeMapper {

  public static Cidade fromDTO(RegistroCidadeDTO cidadeDTO) {
    return new Cidade(
      null, 
      cidadeDTO.getNome(), 
      new Estado(cidadeDTO.getEstadoId()),
      null
    );
  }

  public static CidadeDTO fromEntity(Cidade cidade) {
    return new CidadeDTO(
      cidade.getId(), cidade.getNome(),
      EstadoMapper.fromEntity(cidade.getEstado())
    );
  }

  public static CidadeDetailsDTO fromEntityDetails(Cidade cidade) {
    return new CidadeDetailsDTO(
      cidade.getId(), 
      cidade.getNome(),
      EstadoMapper.fromEntity(cidade.getEstado()),
      cidade.getRegistros().stream()
        .parallel()
        .map(RegistroBuracoMapper::fromSimpleEntity)
        .collect(Collectors.toList())
    );
  }

  public static SimpleCidadeDTO fromSimpleEntity(Cidade cidade) {
    return new SimpleCidadeDTO(cidade.getId(), cidade.getNome());
  }
}
