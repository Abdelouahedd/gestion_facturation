package com.ae.gestion.facture.facture.service.impl;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.repository.FactureRepository;
import com.ae.gestion.facture.facture.service.FactureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;

    @Override
    public Facture createFacture(Facture facture) {
        return this.factureRepository.saveAndFlush(facture);
    }

    @Override
    public Facture updateFacture(Facture facture,Long id) {
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

}
