package com.ae.gestion.facture.facture.web;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.FactureService;
import com.ae.gestion.facture.facture.service.dto.FactureDto;
import com.ae.gestion.facture.facture.service.dto.mapper.FactureMapper;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class FactureRestController {
  private final FactureService factureService;
  private final FactureMapper factureMapper;
  @GetMapping(path = "/facture/{id}")
  public ResponseEntity<FactureDto>getFacture(@PathVariable Long id) {
    return ResponseEntity.ok(this.factureService.getFacture(id));
  }

  @PostMapping(path = "/facture")
  public ResponseEntity<FactureDto>addFacture(@RequestBody FactureRequest facture) {
    return ResponseEntity.ok(this.factureService.createFacture(facture));
  }

  @PutMapping(path = "/facture/{id}")
  public ResponseEntity<FactureDto>updateFacture(@RequestBody FactureRequest factureRequest, @PathVariable Long id) {
    return ResponseEntity.ok(this.factureService.updateFacture(factureRequest, id));
  }


  @GetMapping(path = "/factures")
  public ResponseEntity<Page<FactureDto>> getFactures(
    @Or({
      @Spec(path = "total", params = "total", spec = Equal.class),
      @Spec(path = "complete", params = "complete", spec = Equal.class),
      @Spec(path = "client.nom", params = "client", spec = Equal.class),
    })
      Specification<Facture>specification, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<FactureDto>factures = this.factureService.findAll(specification, pageable);
    return ResponseEntity.ok(factures);
  }

  @DeleteMapping(path = "/facture/{id}")
  public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
    this.factureService.deleteFacture(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
