package com.hole.dto.util;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthDTO {
  
  @NotBlank
  @NotEmpty
  private String email;
  
  @NotBlank
  @NotEmpty
  private String senha;
}
