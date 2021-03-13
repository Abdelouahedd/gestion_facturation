package com.ae.gestion.facture.virement.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VirmentFC implements Serializable {
    @Column(name = "client_id")
    private Long idClient;
    @Column(name = "facture_id")
    private Long idFacture;
}
