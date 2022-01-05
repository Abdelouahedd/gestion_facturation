package com.ae.gestion.facture.virement.service.impl;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.repository.FactureRepository;
import com.ae.gestion.facture.virement.domaine.Virment;
import com.ae.gestion.facture.virement.repository.VirmentRepository;
import com.ae.gestion.facture.virement.service.VirmentService;
import com.ae.gestion.facture.virement.service.dto.VirmentDto;
import com.ae.gestion.facture.virement.service.dto.mapper.VirmentMapper;
import com.ae.gestion.facture.virement.web.request.VirmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VirmentServiceImpl implements VirmentService {
  private final VirmentRepository virmentRepository;
  private final FactureRepository factureRepository;
  private final VirmentMapper virmentMapper;

  @Override
  public VirmentDto addVirment(VirmentRequest virmentRequest) {
    Facture facture = this.factureRepository.getOne(virmentRequest.getIdFacture());
    Virment virment = prepareVirment(virmentRequest, facture);
    return this.virmentMapper.virmentToVirmentDTO(this.virmentRepository.saveAndFlush(virment));
  }

  @Override
  public Page<VirmentDto> getVirment(Specification<Virment> specification, Pageable pageable) {
    return this.virmentRepository
      .findAll(specification, pageable)
      .map(this.virmentMapper::virmentToVirmentDTO);
  }

  private Virment prepareVirment(VirmentRequest virmentRequest, Facture facture) {
    Virment virment = new Virment();
    virment.setFacture(facture);
    virment.setPrix(virmentRequest.getPrix());
    return virment;
  }
}
