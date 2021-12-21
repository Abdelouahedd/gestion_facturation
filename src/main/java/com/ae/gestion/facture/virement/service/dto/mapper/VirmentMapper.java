package com.ae.gestion.facture.virement.service.dto.mapper;

import com.ae.gestion.facture.virement.domaine.Virment;
import com.ae.gestion.facture.virement.service.dto.VirmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VirmentMapper {
    VirmentMapper INSTANCE = Mappers.getMapper(VirmentMapper.class);

    @Mapping(source = "facture.total",target = "total")
    @Mapping(source = "facture.document",target = "document")
    @Mapping(source = "prix",target = "virment")
    VirmentDto virmentToVirmentDTO(Virment virment);
}
