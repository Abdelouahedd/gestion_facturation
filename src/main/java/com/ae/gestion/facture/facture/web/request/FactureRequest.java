package com.ae.gestion.facture.facture.web.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FactureRequest implements Serializable {
  private final Double total;
  private final Long documentId;
  private final Boolean complete=false;
}
