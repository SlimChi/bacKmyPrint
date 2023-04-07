package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.InsertIntervenirDto;
import fr.rt.MyPrintRed.dto.IntervenirDto;
import fr.rt.MyPrintRed.entities.Intervenir;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IntervenirMapper {


    IntervenirDto toDto(Intervenir intervenir);

    Intervenir toEntity(IntervenirDto intervenirDto);

    Intervenir toEntity(InsertIntervenirDto insertDto);

    List<IntervenirDto> toDtoList(List<Intervenir> intervenirList);
}
