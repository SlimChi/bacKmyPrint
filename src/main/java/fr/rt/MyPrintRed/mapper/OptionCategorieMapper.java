package fr.rt.MyPrintRed.mapper;


import fr.rt.MyPrintRed.dto.OptionCategorieDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.OptionCategorie;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {TypeOptionMapper.class})
public interface OptionCategorieMapper {

    OptionCategorieDto toDto(OptionCategorie optionCategorie);

    OptionCategorie toEntity(OptionCategorieDto optionCategorieDto);

    OptionCategorie toEntity(Integer idCategorie, TypeOptionDto typeOptionDto);

    OptionCategorie toEntityWithIds(Integer idCategorie, Integer idOption,Integer idTypeOption);
    List<OptionCategorieDto> toDtoList(List<OptionCategorie> optionCategories);
}
