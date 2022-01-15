package com.ae.gestion.facture.facture.service;


import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.dto.FactureDto;
import com.ae.gestion.facture.facture.service.dto.FactureMonth;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface FactureService {
  FactureDto createFacture(FactureRequest factureRequest);

  FactureDto updateFacture(FactureRequest facture, Long id);

  FactureDto getFacture(Long id);

  FactureDto getFacture(Double total);

  Page<FactureDto> findAll(Specification<Facture> specification, Pageable pageable);

  List<FactureDto> getListFacture(Specification<Facture> specification);

  void deleteFacture(Long id);

  List<String>getDatesFacture();
  List<FactureMonth> getNumberFactureByMonth(String year);
}
