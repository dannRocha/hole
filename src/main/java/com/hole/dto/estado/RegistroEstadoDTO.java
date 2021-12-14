package com.hole.dto.estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEstadoDTO {
  private String uf;

  private String nome;

  private Long regiaoId;
}
