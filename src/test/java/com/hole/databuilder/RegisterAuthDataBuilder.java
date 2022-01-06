package com.hole.databuilder;

import com.hole.dto.util.RegistroAuthDTO;

public class RegisterAuthDataBuilder {
  private RegistroAuthDTO register;

  private RegisterAuthDataBuilder(RegistroAuthDTO register) {
    this.register = register;
  }

  public static RegisterAuthDataBuilder aRegister() {
    return new RegisterAuthDataBuilder(new RegistroAuthDTO());
  }

  public RegisterAuthDataBuilder withValidRegisterAuth() {
    register.setEmail("email@example.com");
    register.setSenha("1234");
    register.setConfirmacaoSenha("1234");
    register.setPerfilId(2L);

    return this;
  }

  public RegisterAuthDataBuilder withInValidRegisterAuth() {
    register.setEmail("email.com");
    register.setSenha("12341");
    register.setConfirmacaoSenha("1234");
    register.setPerfilId(null);
    
    return this;
  }
  
  public RegistroAuthDTO build() {
    return register;
  }

}
