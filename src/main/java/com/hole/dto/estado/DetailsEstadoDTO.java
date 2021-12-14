package com.hole.dto.estado;

import java.util.List;

import com.hole.dto.cidade.SimpleCidadeDTO;
import com.hole.dto.regiao.RegiaoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsEstadoDTO {
  private Long id;
  private String uf;
  private String nome;
  private RegiaoDTO regiao;
  private List<SimpleCidadeDTO> cidades;
}
