package com.ae.gestion.facture.virement.web.request;

import lombok.Data;

@Data
public class VirmentRequest {
    private Long idFacture;
    private Long idClient;
    private Double prix;
}
