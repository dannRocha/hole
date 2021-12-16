package com.hole.exceptions;

import lombok.Getter;

@Getter
public class PasswordRegisterException extends HoleException {
  private String message;

  public PasswordRegisterException() {
    this("Senha e confirmação de senha são diferentes");
  }

  public PasswordRegisterException(String message) {
    super(message);
    this.message = message;
  }
}
