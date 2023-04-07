package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.OptionDto;
import fr.rt.MyPrintRed.entities.Option;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OptionMapper {

    OptionDto toDto(Option option);

    Option toEntity (OptionDto optionDto);

    List<OptionDto> toDtoList(List<Option> options);
}
