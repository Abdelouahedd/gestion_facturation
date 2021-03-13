package com.ae.gestion.facture.client.service.impl;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.client.repository.ClientRepository;
import com.ae.gestion.facture.client.service.ClientService;
import com.ae.gestion.facture.commun.domaine.StateEnum;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        log.info("AddClient : " + client.toString());
        return this.clientRepository.saveAndFlush(client);
    }

    @Override
    public Client updateClient(Client client) {
        log.info("updateClient : " + client.toString());
        return this.clientRepository.save(client);
    }

    @Override
    public Client getClient(Long id) {
        log.info("getClient : " + id);
        return this.clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public Client getClient(String nom) {
        log.info("getClient : " + nom);
        return this.clientRepository.findByNom(nom).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public List<Client> getClients() {
        log.info("getClients : ");
        return this.clientRepository.findAll();
    }

    @Override
    public Page<Client> getClients(Pageable pageable) {
        log.info("getClients pageable : ");
        return this.clientRepository.findAll(pageable);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = this.getClient(id);
        client.setState(StateEnum.DELETED);
        this.clientRepository.save(client);
    }
}
