package fr.rt.MyPrintRed.mapper;


import fr.rt.MyPrintRed.dto.FichierDto;
import fr.rt.MyPrintRed.entities.Fichier;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FichierMapper {


    FichierDto toDto(Fichier fichier);

    List<FichierDto> toDtoList(List<Fichier> fichiers);

    Fichier toFichierOnlyId(FichierDto fichierDto);
}
