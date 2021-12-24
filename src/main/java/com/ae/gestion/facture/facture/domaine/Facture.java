package com.ae.gestion.facture.facture.domaine;

import com.ae.gestion.facture.client.domaine.Client;
import com.ae.gestion.facture.commun.domaine.AbstractAuditingEntity;
import com.ae.gestion.facture.document.domaine.Document;
import com.ae.gestion.facture.virement.domaine.Virment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facture")
@Where(clause = "state <> 'DELETED'")
@SQLDelete(sql = "UPDATE facture SET state = 'DELETED' WHERE id=?")
public class Facture extends AbstractAuditingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double total;
  @OneToOne(targetEntity = Document.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "document_id")
  private Document document;
  private Boolean complete = false;
  @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Virment> virments = new ArrayList<>();
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;

}
