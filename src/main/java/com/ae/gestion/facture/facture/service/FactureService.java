package com.ae.gestion.facture.facture.service;


import com.ae.gestion.facture.facture.domaine.Facture;

public interface FactureService {
    Facture createFacture(Facture facture);

    Facture updateFacture(Facture facture, Long id);

    Facture getFacture(Long id);

    Facture getFacture(Double total);
}
