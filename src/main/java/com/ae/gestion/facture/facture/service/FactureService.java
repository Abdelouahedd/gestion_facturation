package com.ae.gestion.facture.facture.service;


import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface FactureService {
  Facture createFacture(FactureRequest factureRequest);

  Facture updateFacture(FactureRequest facture, Long id);

  Facture getFacture(Long id);

  Facture getFacture(Double total);

  Page<Facture> findAll(Specification specification, Pageable pageable);
}
