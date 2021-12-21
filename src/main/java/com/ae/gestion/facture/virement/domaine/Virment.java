package com.ae.gestion.facture.virement.domaine;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import com.ae.gestion.facture.facture.domaine.Facture;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "virment")
@Data
@Where(clause = "state <> 'DELETED'")
@SQLDelete(sql = "UPDATE virment SET state = 'DELETED' WHERE client_id=? and facture_id=?")
public class Virment extends AbstractAuditingEntity {
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
