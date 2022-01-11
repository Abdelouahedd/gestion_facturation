package com.ae.gestion.facture.virement.domaine;

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
@SQLDelete(sql = "UPDATE virment SET state = 'DELETED' WHERE id=?")
public class Virment extends AbstractAuditingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "facture_id")
  private Facture facture;
  private Double prix;
}
