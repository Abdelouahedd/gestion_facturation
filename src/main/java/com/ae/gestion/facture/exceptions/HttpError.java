package com.ae.gestion.facture.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HttpError {
  private final Integer code;
  private final String message;
}
