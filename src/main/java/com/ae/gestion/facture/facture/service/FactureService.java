package com.ae.gestion.facture.facture.service;


import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.dto.FactureDto;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface FactureService {
  FactureDto createFacture(FactureRequest factureRequest);

  FactureDto updateFacture(FactureRequest facture, Long id);

  FactureDto getFacture(Long id);

  FactureDto getFacture(Double total);

  Page<FactureDto> findAll(Specification<Facture> specification, Pageable pageable);

  void deleteFacture(Long id);
}
