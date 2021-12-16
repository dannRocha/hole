package com.hole.dto.estado;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEstadoDTO {
  
  @Size(min = 2, max = 2)
  @NotBlank
  private String uf;

  @Size(max = 30)
  @NotBlank
  private String nome;

  @NotNull
  private Long regiaoId;
}
