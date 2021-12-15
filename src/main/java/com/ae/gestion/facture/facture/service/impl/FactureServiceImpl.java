package com.ae.gestion.facture.facture.service.impl;

import com.ae.gestion.facture.document.domaine.Document;
import com.ae.gestion.facture.document.service.DocumentService;
import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.repository.FactureRepository;
import com.ae.gestion.facture.facture.service.FactureService;
import com.ae.gestion.facture.facture.service.dto.mapper.FactureMapper;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class FactureServiceImpl implements FactureService {
  private final FactureRepository factureRepository;
  private final FactureMapper factureMapper;
  private final DocumentService documentService;

  @Override
  public Facture createFacture(FactureRequest factureRequest) {
    Facture facture = extractFacture(factureMapper, factureRequest, this.documentService);
    return this.factureRepository.saveAndFlush(facture);
  }

  private Facture extractFacture(FactureMapper factureMapper, FactureRequest factureRequest, DocumentService documentService) {
    Facture facture = factureMapper.factureRequestToFacture(factureRequest);
    Optional<Long> idDoc = Optional.ofNullable(factureRequest.getDocumentId());
    System.out.println(idDoc);
    idDoc.ifPresentOrElse(
      (documentId) -> {
        Document document = documentService.getDocument(documentId);
        facture.setDocument(document);
      },
      () -> log.info("No document for the invoice"));
    return facture;
  }

  @Override
  public Facture updateFacture(FactureRequest factureRequest, Long id) {
    Facture facture = extractFacture(factureMapper, factureRequest, this.documentService);
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
  public Page<Facture> findAll(Specification<Facture> specification, Pageable pageable) {
    return this.factureRepository.findAll(specification, pageable);
  }
}
