package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.TypeAdresseDto;
import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.TypeAdresse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TypeAdresseMapper {

    TypeAdresseDto toDto(TypeAdresse typeAdresse);

    TypeAdresse toEntity(TypeAdresseDto typeAdresseDto);
}
