package com.hole.dto.cidade;

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
public class RegistroCidadeDTO {

  @Size(max = 255)
  @NotBlank
  private String nome;

  @NotNull
  private Long estadoId;
}
