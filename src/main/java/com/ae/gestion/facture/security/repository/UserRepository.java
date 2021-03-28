package com.ae.gestion.facture.security.repository;

import com.ae.gestion.facture.security.domaine.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserClient, Long> {
    Optional<UserClient> findByUsername(String username);
}
