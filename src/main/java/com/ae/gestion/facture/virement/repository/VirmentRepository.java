package com.ae.gestion.facture.virement.repository;

import com.ae.gestion.facture.virement.domaine.Virment;
import com.ae.gestion.facture.virement.domaine.VirmentFC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VirmentRepository extends JpaRepository<Virment, VirmentFC>, JpaSpecificationExecutor<Virment> {

    Optional<Virment> findByFacture(Long idFacture);

    Optional<Virment> findByClient(Long idClient);

}
