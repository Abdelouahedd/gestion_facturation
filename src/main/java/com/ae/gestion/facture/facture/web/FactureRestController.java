package com.ae.gestion.facture.facture.web;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.FactureService;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
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
public class FactureRestController {
  private final FactureService factureService;

  @GetMapping(path = "/facture/{id}")
  public ResponseEntity<Facture> getFacture(@PathVariable Long id) {
    return ResponseEntity.ok(this.factureService.getFacture(id));
  }

  @PostMapping(path = "/facture")
  public ResponseEntity<Facture> addFacture(@RequestBody FactureRequest facture) {
    return ResponseEntity.ok(this.factureService.createFacture(facture));
  }

  @PutMapping(path = "/facture/{id}")
  public ResponseEntity<Facture> updateFacture(@RequestBody FactureRequest factureRequest, @PathVariable Long id) {
    return ResponseEntity.ok(this.factureService.updateFacture(factureRequest, id));
  }

/*  @GetMapping(path = "/facture")
  public ResponseEntity<Facture> getFacture(@RequestParam("total") Double total) {
    return ResponseEntity.ok(this.factureService.getFacture(total));
  }*/

  @GetMapping(path = "/factures")
  public ResponseEntity<Page<Facture>> getFactures(
    @Or({
      @Spec(path = "total", params = "q", spec = LikeIgnoreCase.class),
      @Spec(path = "complete", params = "q", spec = Equal.class)
    })
      Specification specification,
    Pageable pageable) {
    Page<Facture> factures = this.factureService.findAll(specification, pageable);
    return ResponseEntity.ok(factures);
  }
}
