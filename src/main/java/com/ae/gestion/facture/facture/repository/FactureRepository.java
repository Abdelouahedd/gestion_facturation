package com.ae.gestion.facture.facture.repository;

import com.ae.gestion.facture.facture.domaine.Facture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>, JpaSpecificationExecutor<Facture> {

  Optional<Facture> findByTotal(Double total);

  Page<Facture> findAll(Specification specification, Pageable pageable);
}
