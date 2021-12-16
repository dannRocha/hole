package com.hole.dto.buraco;

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
public class RegistroBuracoDTO {
  
  private String descricao;
  
  @NotBlank
  @Size(max = 20)
  private String latitude;
  
  @NotBlank
  @Size(max = 20)
  private String logitude;
  
  @NotNull
  private Long cidadeId;
}
