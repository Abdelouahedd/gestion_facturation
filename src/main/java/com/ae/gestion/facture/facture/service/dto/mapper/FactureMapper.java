package com.ae.gestion.facture.facture.service.dto.mapper;

import com.ae.gestion.facture.facture.service.dto.FactureDto;
import com.ae.gestion.facture.facture.web.request.FactureRequest;
import com.ae.gestion.facture.facture.domaine.Facture;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FactureMapper {
  Facture factureRequestToFacture(FactureRequest factureRequest);

  @Mapping(source = "document.id",target = "documentId")
  @Mapping(source = "client.nom",target = "nom")
  FactureDto factureToFactureDto(Facture facture);

  FactureRequest factureToFactureRequest(Facture facture);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateFactureFromFactureRequest(FactureRequest factureRequest, @MappingTarget Facture facture);
}
