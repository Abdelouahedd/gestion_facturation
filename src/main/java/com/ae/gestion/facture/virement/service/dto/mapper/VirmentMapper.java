package com.ae.gestion.facture.virement.service.dto.mapper;

import com.ae.gestion.facture.virement.domaine.Virment;
import com.ae.gestion.facture.virement.service.dto.VirmentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VirmentMapper {
  VirmentMapper INSTANCE = Mappers.getMapper(VirmentMapper.class);


  @Mapping(source = "factureCreatedDate", target = "facture.createdDate")
  @Mapping(source = "factureId", target = "facture.id")
  @Mapping(source = "factureTotal", target = "facture.total")
  @Mapping(source = "factureComplete", target = "facture.complete")
  @Mapping(source = "factureClientId", target = "facture.client.id")
  @Mapping(source = "factureClientNom", target = "facture.client.nom")
  @Mapping(source = "factureClientPrenom", target = "facture.client.prenom")
  Virment virmentDtoToVirment(VirmentDto virmentDtoo);

  @InheritInverseConfiguration(name = "virmentDtoToVirment")
  VirmentDto virmentToVirmentDTO(Virment virment);

  @InheritConfiguration(name = "virmentDtoToVirment")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateVirmentFromVirmentDto(VirmentDto virmentDtoo, @MappingTarget Virment virment);
}
