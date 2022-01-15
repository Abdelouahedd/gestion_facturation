package com.ae.gestion.facture.virement.web;

import com.ae.gestion.facture.facture.service.dto.TotalPerMonth;
import com.ae.gestion.facture.virement.domaine.Virment;
import com.ae.gestion.facture.virement.service.VirmentService;
import com.ae.gestion.facture.virement.service.dto.VirmentDto;
import com.ae.gestion.facture.virement.web.request.VirmentRequest;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class VirmentRestController {
  private final VirmentService virmentService;

  @PostMapping("/virment")
  public VirmentDto addVirment(@RequestBody VirmentRequest virmentRequest) {
    return this.virmentService.addVirment(virmentRequest);
  }

  @PutMapping("/virment/{id}")
  public VirmentDto updateVirment(@RequestBody VirmentRequest virmentRequest, @PathVariable("id") Long id) {
    return this.virmentService.updateVirment(virmentRequest, id);
  }

  @DeleteMapping("/virment/{id}")
  public ResponseEntity<Void> deletVirment(@PathVariable("id") Long id) {
    this.virmentService.deleteVirment(id);
    return ResponseEntity.noContent().build();
  }


  @GetMapping("/virments")
  public Page<VirmentDto> getVirmentBySearch(
    @Or(
      {
        @Spec(path = "facture.client.nom", params = "q", spec = LikeIgnoreCase.class),
        @Spec(path = "facture.client.prenom", params = "q", spec = LikeIgnoreCase.class)
      }
    ) Specification<Virment> specification,
    @PageableDefault(sort = "facture.id", direction = Sort.Direction.DESC) Pageable pageable
  ) {
    return this.virmentService.getVirment(specification, pageable);
  }

  @GetMapping("/virments/total/month")
  public List<TotalPerMonth> getTotalVirmentPerMonth() {
    return this.virmentService.getTotalVirmentPerMonth();
  }
}
