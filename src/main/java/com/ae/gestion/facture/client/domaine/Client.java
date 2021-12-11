package com.ae.gestion.facture.client.domaine;

import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "client")
@Where(clause = "state <> 'DELETED'")
public class Client extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
}
