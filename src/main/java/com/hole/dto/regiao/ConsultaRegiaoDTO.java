package com.hole.dto.regiao;

import java.util.List;

import com.hole.dto.estado.SimpleEstadoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaRegiaoDTO extends RegiaoDTO{
  private List<SimpleEstadoDTO> estados;
}
