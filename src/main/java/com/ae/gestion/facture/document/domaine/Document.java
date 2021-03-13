package com.ae.gestion.facture.document.domaine;

import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import com.ae.gestion.facture.facture.domaine.Facture;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "document")
@Where(clause = "state <> 'DELETED'")
public class Document extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    @OneToOne(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Facture facture;
}
