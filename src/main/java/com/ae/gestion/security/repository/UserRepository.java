package com.ae.gestion.security.repository;

import com.ae.gestion.security.domaine.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserClient, Long> {
    Optional<UserClient> findByUsername(String username);
}
