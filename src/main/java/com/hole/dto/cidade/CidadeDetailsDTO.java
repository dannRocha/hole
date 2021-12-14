package com.hole.dto.cidade;

import java.util.List;

import com.hole.dto.buraco.SimpleRegistroBuracoDTO;
import com.hole.dto.estado.EstadoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDetailsDTO {
  private Long id;
  private String nome;
  private EstadoDTO estado;
  private List<SimpleRegistroBuracoDTO> registros;   
}
