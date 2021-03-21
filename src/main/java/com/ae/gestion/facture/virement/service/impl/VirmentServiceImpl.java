package com.ae.gestion.facture.virement.service.impl;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.client.service.ClientService;
import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.FactureService;
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
    private final FactureService factureService;
    private final ClientService clientService;
    private final VirmentMapper virmentMapper;

    @Override
    public VirmentDto addVirment(VirmentRequest virmentRequest) {
        Facture facture = this.factureService.getFacture(virmentRequest.getIdFacture());
        Client client = this.clientService.getClient(virmentRequest.getIdClient());
        Virment virment = prepareVirment(virmentRequest, facture, client);
        return this.virmentMapper.virmentToVirmentDTO(this.virmentRepository.saveAndFlush(virment));
    }

    @Override
    public Page<VirmentDto> getVirment(Specification<Virment> specification, Pageable pageable) {
        return this.virmentRepository
                .findAll(specification, pageable)
                .map(this.virmentMapper::virmentToVirmentDTO);
    }

    private Virment prepareVirment(VirmentRequest virmentRequest, Facture facture, Client client) {
        Virment virment = new Virment();
        virment.setClient(client);
        virment.setFacture(facture);
        virment.setPrix(virmentRequest.getPrix());
        return virment;
    }
}
