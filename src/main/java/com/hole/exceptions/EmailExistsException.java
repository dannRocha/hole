package com.hole.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailExistsException extends HoleException {
  private String message;

  public EmailExistsException() {
    this("Email indisponivel");
  }

  public EmailExistsException(String message) {
    super(message);
    this.message = message;
  }

}
