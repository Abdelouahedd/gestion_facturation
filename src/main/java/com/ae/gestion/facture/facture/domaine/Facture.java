package com.ae.gestion.facture.facture.domaine;

import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import com.ae.gestion.facture.document.domaine.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facture")
@Where(clause = "state <> 'DELETED'")
public class Facture extends AbstractAuditingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double total;
  @OneToOne(targetEntity = Document.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "document_id")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private Document document;
  private Boolean complete = false;
}
