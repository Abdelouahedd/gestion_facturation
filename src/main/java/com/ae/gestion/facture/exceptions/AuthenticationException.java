package com.ae.gestion.facture.exceptions;

public class AuthenticationException extends RuntimeException {
  public AuthenticationException() {
    super("Vous n'avez pas le droit d'acceder au ressources");
  }

  public AuthenticationException(String msg) {
    super(msg);
  }
}
