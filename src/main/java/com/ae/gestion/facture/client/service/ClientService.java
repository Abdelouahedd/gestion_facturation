package com.ae.gestion.facture.client.service;

import com.ae.gestion.facture.client.domaine.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);

    Client updateClient(Client client);

    Client getClient(Long id);

    Client getClient(String nom);

    List<Client> getClients();

    Page<Client> getClients(Pageable pageable);

    void deleteClient(Long id);
}
