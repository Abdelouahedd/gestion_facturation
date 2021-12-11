package com.ae.gestion.facture.document.repository;

import com.ae.gestion.facture.document.domaine.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
