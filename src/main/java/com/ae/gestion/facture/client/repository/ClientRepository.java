package com.ae.gestion.facture.client.repository;

import com.ae.gestion.facture.client.domaine.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNom(String nom);
}
