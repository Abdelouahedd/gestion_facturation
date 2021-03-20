package com.ae.gestion.facture.client.web;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.client.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

    @GetMapping(path = "/clients")
    public Page<Client> getAllClient(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return this.clientService.getClients(pageable);
    }

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client = this.clientService.getClient(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping(path = "/client")
    public ResponseEntity<Client> getClientByNom(@RequestParam("nom") String nom) {
        Client client = this.clientService.getClient(nom);
        return ResponseEntity.ok(client);
    }

    @PostMapping(path = "/client")
    public ResponseEntity<Client> addClient(Client client) {
        Client savedClient = this.clientService.addClient(client);
        return ResponseEntity.ok(savedClient);
    }

    @PutMapping(path = "/client")
    public ResponseEntity<Client> updateClient(Client client) {
        Client savedClient = this.clientService.updateClient(client);
        return ResponseEntity.ok(savedClient);
    }

    @DeleteMapping(path = "/client/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long id) {
        this.clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
