package com.ae.gestion.facture.facture.domaine;

import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import com.ae.gestion.facture.document.domaine.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "facture")
@Where(clause = "state <> 'DELETED'")
public class Facture extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    @OneToOne(targetEntity = Document.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;
    private Boolean complete = false;
}
