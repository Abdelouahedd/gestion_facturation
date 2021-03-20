package com.ae.gestion.facture.facture.web;

import com.ae.gestion.facture.facture.domaine.Facture;
import com.ae.gestion.facture.facture.service.FactureService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<Facture> addFacture(@RequestBody Facture facture) {
        return ResponseEntity.ok(this.factureService.createFacture(facture));
    }

    @PutMapping(path = "/facture/{id}")
    public ResponseEntity<Facture> updateFacture(@RequestBody Facture facture, @PathVariable Long id) {
        return ResponseEntity.ok(this.factureService.updateFacture(facture, id));
    }

    @GetMapping(path = "/facture}")
    public ResponseEntity<Facture> getFacture(@RequestParam("total") Double total) {
        return ResponseEntity.ok(this.factureService.getFacture(total));
    }
}
