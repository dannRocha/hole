package com.hole.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends HoleException {
  
  private String message;

  public EntityNotFoundException() {
    this("Recurso não encontrado");
  }

  public EntityNotFoundException(String message) {
    super(message);
    this.message = message;
  }
}
