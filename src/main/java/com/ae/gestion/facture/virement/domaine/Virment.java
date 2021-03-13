package com.ae.gestion.facture.virement.domaine;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.facture.domaine.Facture;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "virment")
@Data
public class Virment {
    @EmbeddedId
    private VirmentFC id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("facture_id")
    @JoinColumn(name = "facture_id")
    private Facture facture;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("client_id")
    @JoinColumn(name = "client_id")
    private Client client;
    private Double prix;
}
