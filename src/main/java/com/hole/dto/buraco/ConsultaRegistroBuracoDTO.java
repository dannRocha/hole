package com.hole.dto.buraco;

import com.hole.dto.cidade.CidadeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaRegistroBuracoDTO {
  private Long id;
  private String descricao;
  private String latitude;
  private String logitude;
  private CidadeDTO cidade;
}
