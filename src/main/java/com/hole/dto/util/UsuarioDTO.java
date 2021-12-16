package com.hole.dto.util;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

  @NotBlank
  @NotNull
  private String email;

  @NotBlank
  @NotNull
  private String senha;

  @NotNull
  private Long perfilId;
}
