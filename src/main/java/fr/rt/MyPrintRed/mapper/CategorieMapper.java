package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.CategorieDto;
import fr.rt.MyPrintRed.entities.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CategorieMapper {

    CategorieDto toDto(Categorie categorie);

    Categorie toEntity(CategorieDto categorieDto);

    List<CategorieDto> toListDto(List<Categorie> categories);
}
