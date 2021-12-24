package com.ae.gestion.facture.virement.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class VirmentDto implements Serializable {
  private final Instant createdDate;
  private final Long id;
  private final Instant factureCreatedDate;
  private final Long factureId;
  private final Double factureTotal;
  private final Boolean factureComplete;
  private final Long factureClientId;
  private final String factureClientNom;
  private final String factureClientPrenom;
  private final Double prix;
}
