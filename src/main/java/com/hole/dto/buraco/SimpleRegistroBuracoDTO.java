package com.hole.dto.buraco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRegistroBuracoDTO {
  private String descricao;
  private String latitude;
  private String logitude;
}
