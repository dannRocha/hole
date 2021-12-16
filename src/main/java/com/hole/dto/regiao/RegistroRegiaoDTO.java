package com.hole.dto.regiao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRegiaoDTO {
  
  @Size(max = 5)
  @NotBlank
  private String sigla;

  @Size(max = 25)
  @NotBlank
  private String nome;
}
