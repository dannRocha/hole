package com.hole.mappers;

import java.util.stream.Collectors;

import com.hole.dto.estado.DetailsEstadoDTO;
import com.hole.dto.estado.EstadoDTO;
import com.hole.dto.estado.RegistroEstadoDTO;
import com.hole.entities.Estado;
import com.hole.entities.Regiao;

public class EstadoMapper {
  public static Estado fromDTO(RegistroEstadoDTO registroDTO) {
    return new Estado(null, 
      registroDTO.getUf(), 
      registroDTO.getNome(), 
      new Regiao(registroDTO.getRegiaoId()),
      null
    );
  }

  public static EstadoDTO fromEntity(Estado estado) {
    return new EstadoDTO(
      estado.getId(), estado.getUf(), estado.getNome(),
      RegiaoMapper.fromEntity(estado.getRegiao())
    );
  }

  public static DetailsEstadoDTO fromDetailsEntity(Estado estado) {
    return new DetailsEstadoDTO(
      estado.getId(), 
      estado.getUf(),
      estado.getNome(),
      RegiaoMapper.fromEntity(estado.getRegiao()),
      estado.getCidades()
        .stream()
        .parallel()
        .map(CidadeMapper::fromSimpleEntity)
        .collect(Collectors.toList())
    );
  }
}
