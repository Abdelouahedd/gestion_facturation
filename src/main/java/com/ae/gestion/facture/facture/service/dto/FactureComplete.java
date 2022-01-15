package com.ae.gestion.facture.facture.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FactureComplete {
  private Long number;
  private String status;
}
