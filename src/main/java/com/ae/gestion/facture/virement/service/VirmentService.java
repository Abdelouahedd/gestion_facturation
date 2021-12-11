package com.ae.gestion.facture.virement.service;

import com.ae.gestion.facture.virement.domaine.Virment;
import com.ae.gestion.facture.virement.service.dto.VirmentDto;
import com.ae.gestion.facture.virement.web.request.VirmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface VirmentService {
    VirmentDto addVirment(VirmentRequest virmentRequest);

    Page<VirmentDto> getVirment(Specification<Virment> specification, Pageable pageable);
}
