package com.hole.dto.estado;

import com.hole.dto.regiao.RegiaoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {
  
  private Long id;

  private String uf;

  private String nome;

  private RegiaoDTO regiao;
}
