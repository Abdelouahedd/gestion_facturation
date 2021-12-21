package com.ae.gestion.facture.facture.service.dto;

import com.ae.gestion.facture.commun.domaine.StateEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class FactureDto implements Serializable {
  private final StateEnum state;
  private final Long id;
  private final Double total;
  private final Long documentIdId;
  private final Boolean complete;
  private final Instant createdDate;
}
