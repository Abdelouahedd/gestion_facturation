package com.ae.gestion.facture.facture.service.impl;

import com.ae.gestion.facture.document.domaine.Document;
import com.ae.gestion.facture.document.service.DocumentService;
import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.repository.FactureRepository;
import com.ae.gestion.facture.facture.service.FactureService;
import com.ae.gestion.facture.facture.service.dto.FactureDto;
import com.ae.gestion.facture.facture.service.dto.mapper.FactureMapper;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class FactureServiceImpl implements FactureService {
  private final FactureRepository factureRepository;
  private final FactureMapper factureMapper;
  private final DocumentService documentService;

  @Override
  public FactureDto createFacture(FactureRequest factureRequest) {
    Facture facture = extractFacture(factureMapper, factureRequest, this.documentService);
    Facture savedFacture = this.factureRepository.saveAndFlush(facture);
    return this.factureMapper.factureToFactureDto(savedFacture);
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
  public FactureDto updateFacture(FactureRequest factureRequest, Long id) {
    Facture facture = extractFacture(factureMapper, factureRequest, this.documentService);
    facture.setId(id);
    Facture savedFacture = this.factureRepository.saveAndFlush(facture);
    return this.factureMapper.factureToFactureDto(savedFacture);
  }

  @Override
  public FactureDto getFacture(Long id) {
    return this.factureRepository.findById(id).map(factureMapper::factureToFactureDto)
      .orElseThrow(() -> new RuntimeException("Facture not found !!"));
  }

  @Override
  public FactureDto getFacture(Double total) {
    return this.factureRepository.findByTotal(total).map(factureMapper::factureToFactureDto)
      .orElseThrow(() -> new RuntimeException("Facture not found !!"));
  }

  @Override
  public Page<FactureDto> findAll(Specification<Facture> specification, Pageable pageable) {
    return this.factureRepository.findAll(specification, pageable).map(factureMapper::factureToFactureDto);
  }

  @Override
  public void deleteFacture(Long id) {
    this.factureRepository.deleteById(id);
  }


  @Override
  public List<FactureDto> getListFacture(Specification<Facture> specification) {
    return this.factureRepository
      .findAll(specification)
      .stream()
      .map(factureMapper::factureToFactureDto)
      .collect(Collectors.toList());
  }
}
