package com.hole.dto.estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleEstadoDTO {
  private Long id;

  private String uf;

  private String nome; 
}
