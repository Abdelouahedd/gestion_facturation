package com.ae.gestion.facture.document.domaine;

import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "document")
@Where(clause = "state <> 'DELETED'")
@SQLDelete(sql = "UPDATE document SET state = 'DELETED' WHERE id=?")
public class Document extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
}
