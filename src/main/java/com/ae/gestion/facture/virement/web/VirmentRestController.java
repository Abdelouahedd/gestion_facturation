package com.ae.gestion.facture.virement.web;

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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class VirmentRestController {
    private final VirmentService virmentService;

    @PostMapping("/virment")
    public VirmentDto addVirment(@RequestBody VirmentRequest virmentRequest) {
        return this.virmentService.addVirment(virmentRequest);
    }

    @GetMapping("/virments")
    public Page<VirmentDto> getVirmentBySearch(
            @Or(
                    {
                            @Spec(path = "client.nom", params = "q", spec = LikeIgnoreCase.class),
                            @Spec(path = "client.prenom", params = "q", spec = LikeIgnoreCase.class)
                    }
            ) Specification<Virment> specification,
            Pageable pageable
    ) {
        return this.virmentService.getVirment(specification, pageable);
    }
}
