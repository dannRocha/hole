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
public class RegistroAuthDTO {
  
  @NotBlank
  @NotNull
  private String email;

  @NotBlank
  @NotNull
  private String senha;

  @NotBlank
  @NotNull
  private String confirmacaoSenha;

  @NotNull
  private Long perfilId;
  
  public Boolean isValidPassword() {
    return senha != null && confirmacaoSenha != null && senha.equals(confirmacaoSenha);
  }
}
