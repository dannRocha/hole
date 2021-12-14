package com.hole.exceptions;

import lombok.Getter;

@Getter
public class HoleException extends RuntimeException {
 
  private String message;
 
  public HoleException() {
    this("Recurso não encontrado");
  }

  public HoleException(String message) {
    super(message);
    this.message = message;
  }
}
