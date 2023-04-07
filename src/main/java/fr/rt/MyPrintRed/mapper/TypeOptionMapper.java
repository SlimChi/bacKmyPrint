package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.TypeOption;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TypeOptionMapper {

    TypeOptionDto toDto(TypeOption typeOption);

    TypeOption toEntity(TypeOptionDto typeOptionDto);

    List<TypeOptionDto> toDtoList(List<TypeOption> typeOptions);
}
