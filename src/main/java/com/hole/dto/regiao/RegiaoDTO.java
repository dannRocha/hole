package com.hole.dto.regiao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegiaoDTO {
  private Long id;

  private String sigla;

  private String nome;
}
