package com.hole.mappers;

import java.util.stream.Collectors;

import com.hole.dto.estado.SimpleEstadoDTO;
import com.hole.dto.regiao.ConsultaRegiaoDTO;
import com.hole.dto.regiao.RegiaoDTO;
import com.hole.dto.regiao.RegistroRegiaoDTO;
import com.hole.entities.Regiao;

public class RegiaoMapper {
  public static RegiaoDTO fromEntity(Regiao regiao) {
    return new RegiaoDTO(regiao.getId(), regiao.getSigla(), regiao.getNome());
  }

  public static Regiao fromDTO(RegistroRegiaoDTO regiaoDTO ) {
    return new Regiao(null, regiaoDTO.getSigla(), regiaoDTO.getNome(), null);
  }

  public static ConsultaRegiaoDTO fromEntityConsulta(Regiao regiao) {
    
    return new ConsultaRegiaoDTO(){{
      setEstados(
        regiao.getEstados().stream().map(estado -> new SimpleEstadoDTO(estado.getId(), estado.getUf(), estado.getNome())).collect(Collectors.toList()));
      setId(regiao.getId());
      setNome(regiao.getNome());
      setSigla(regiao.getSigla());
    }};
  }

}
