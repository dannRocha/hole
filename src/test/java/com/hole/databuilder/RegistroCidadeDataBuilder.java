package com.hole.databuilder;

import com.hole.dto.cidade.RegistroCidadeDTO;

public class RegistroCidadeDataBuilder {
  
  private RegistroCidadeDTO cidadeDTO;
  
  
  private RegistroCidadeDataBuilder(RegistroCidadeDTO cidadeDTO) {
    this.cidadeDTO = cidadeDTO;
  }

  public static RegistroCidadeDataBuilder aCity() {
    return new RegistroCidadeDataBuilder(new RegistroCidadeDTO());
  }

  public RegistroCidadeDataBuilder withValidContent() {
    cidadeDTO = new RegistroCidadeDTO("Barreirinhas", 16L);
    return this;
  }

  public RegistroCidadeDTO build() {
    return cidadeDTO;
  }
}
