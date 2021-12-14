package com.hole.mappers;

import com.hole.dto.buraco.ConsultaRegistroBuracoDTO;
import com.hole.dto.buraco.RegistroBuracoDTO;
import com.hole.dto.buraco.SimpleRegistroBuracoDTO;
import com.hole.entities.Cidade;
import com.hole.entities.RegistroBuraco;

public class RegistroBuracoMapper {
  public static RegistroBuraco fromDTO(RegistroBuracoDTO registroBuracoDTO) {
    return new RegistroBuraco(
      null, 
      registroBuracoDTO.getDescricao(),
      registroBuracoDTO.getLatitude(),
      registroBuracoDTO.getLogitude(),
      new Cidade(registroBuracoDTO.getCidadeId())
    );
  }
  
  public static ConsultaRegistroBuracoDTO fromEntity(RegistroBuraco registro) {
    return new ConsultaRegistroBuracoDTO(
      registro.getId(), 
      registro.getDescricao(),
      registro.getLatitude(),
      registro.getLogitude(),
      CidadeMapper.fromEntity(registro.getCidade())
    );
  }

  public static SimpleRegistroBuracoDTO fromSimpleEntity(RegistroBuraco registro) {
    return new SimpleRegistroBuracoDTO(
      registro.getDescricao(), 
      registro.getLatitude(),
      registro.getLogitude()
    );
  }
}
