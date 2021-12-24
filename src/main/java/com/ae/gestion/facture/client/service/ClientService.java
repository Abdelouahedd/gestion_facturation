package com.ae.gestion.facture.client.service;

import com.ae.gestion.facture.client.domaine.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ClientService {
  Client addClient(Client client);

  Client updateClient(Client client);

  Client getClient(Long id);

  Page<Client> getClient(Specification<Client> specification, Pageable pageable) ;

  List<Client> getClients(Specification<Client> specification);

  Page<Client> getClients(Pageable pageable);

  void deleteClient(Long id);
}
