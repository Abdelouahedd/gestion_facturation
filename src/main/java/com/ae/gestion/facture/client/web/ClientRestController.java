package com.ae.gestion.facture.client.web;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.client.service.ClientService;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ClientRestController {
  private final ClientService clientService;

/*
  @GetMapping(path = "/clients")
  public Page<Client> getAllClient(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    return this.clientService.getClients(pageable);
  }
*/

  @GetMapping(path = "/client/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
    Client client = this.clientService.getClient(id);
    return ResponseEntity.ok(client);
  }

  @GetMapping(path = "/clients")
  public ResponseEntity<Page<Client>> getClientWithSearch(
    @Or({
      @Spec(path = "nom", params = "q", spec = LikeIgnoreCase.class),
      @Spec(path = "prenom", params = "q", spec = LikeIgnoreCase.class)
    }) Specification<Client> specification, Pageable pageable) {
    Page<Client> client = this.clientService.getClient(specification, pageable);
    return ResponseEntity.ok(client);
  }

  @PostMapping(path = "/client")
  public ResponseEntity<Client> addClient(@RequestBody Client client) {
    Client savedClient = this.clientService.addClient(client);
    return ResponseEntity.ok(savedClient);
  }

  @PutMapping(path = "/client/{id}")
  public ResponseEntity<Client> updateClient(@RequestBody Client client) {
    Client savedClient = this.clientService.updateClient(client);
    return ResponseEntity.ok(savedClient);
  }

  @DeleteMapping(path = "/client/{id}")
  public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long id) {
    this.clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }

}
