package com.ae.gestion.facture.facture.service.impl;

import com.ae.gestion.facture.document.domaine.Document;
import com.ae.gestion.facture.document.service.DocumentService;
import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.repository.FactureRepository;
import com.ae.gestion.facture.facture.service.FactureService;
import com.ae.gestion.facture.facture.service.dto.mapper.FactureMapper;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {
  private final FactureRepository factureRepository;
  private final FactureMapper factureMapper;
  private final DocumentService documentService;

  @Override
  public Facture createFacture(FactureRequest factureRequest) {
    Facture facture = factureMapper.factureRequestToFacture(factureRequest);
    Document document = this.documentService.getDocument(factureRequest.getDocumentId());
    facture.setDocument(document);
    return this.factureRepository.saveAndFlush(facture);
  }

  @Override
  public Facture updateFacture(FactureRequest factureRequest, Long id) {
    Facture facture = factureMapper.factureRequestToFacture(factureRequest);
    Document document = this.documentService.getDocument(factureRequest.getDocumentId());
    facture.setDocument(document);
    facture.setId(id);
    return this.factureRepository.saveAndFlush(facture);
  }

  @Override
  public Facture getFacture(Long id) {
    return this.factureRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Facture not found !!"));
  }

  @Override
  public Facture getFacture(Double total) {
    return this.factureRepository.findByTotal(total)
      .orElseThrow(() -> new RuntimeException("Facture not found !!"));
  }

  @Override
  public Page<Facture> findAll(Specification specification, Pageable pageable) {
    return this.factureRepository.findAll(specification, pageable);
  }
}
