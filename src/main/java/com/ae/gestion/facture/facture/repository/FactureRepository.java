package com.ae.gestion.facture.facture.repository;

import com.ae.gestion.facture.facture.domaine.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    Optional<Facture> findByTotal(Double total);
}
