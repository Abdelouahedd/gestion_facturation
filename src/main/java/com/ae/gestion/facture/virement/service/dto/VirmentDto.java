package com.ae.gestion.facture.virement.service.dto;

import com.ae.gestion.facture.document.domaine.Document;
import lombok.Data;

@Data
public class VirmentDto {
    private Double total;
    private Document document;
    private String nom;
    private String prenom;
    private Double virment;
}
