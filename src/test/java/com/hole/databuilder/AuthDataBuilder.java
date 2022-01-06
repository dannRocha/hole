package com.hole.databuilder;

import com.hole.dto.util.AuthDTO;


public class AuthDataBuilder {
  
  private AuthDTO auth;


  private AuthDataBuilder(AuthDTO auth) {
    this.auth = auth;
  }


  public static AuthDataBuilder aAuth() {
    return new AuthDataBuilder(new AuthDTO());
  }

  public AuthDataBuilder withValidAuth() { 
    auth.setEmail("admin@example.com");
    auth.setSenha("9oL!$_TR@S4");

    return this;
  }

  public AuthDataBuilder withValidAuthFromCommonUser() { 
    auth.setEmail("user@example.com");
    auth.setSenha("9oL!$_TR@S4");

    return this;
  }


  public AuthDataBuilder withInValidAuth() { 
    auth.setEmail("admin@example.com");
    auth.setSenha("111111111");

    return this;
  }
  
  public AuthDataBuilder withInInValidAuthAndBadFormat() { 
    auth.setEmail(null);
    auth.setSenha(null);
    return this;
  }


  public AuthDTO build() {
    return auth;
  }
}
