package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.OptionLigneCommandeDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.OptionLigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {TypeOptionMapper.class})
public interface OptionLigneCommandeMapper {

    OptionLigneCommandeDto toDto(OptionLigneCommande entity);

    OptionLigneCommande toEntity(OptionLigneCommandeDto dto);

    OptionLigneCommande toEntity(Integer numeroCommande, Integer numeroLigneCommande, TypeOptionDto typeOptionDto);

    List<OptionLigneCommandeDto> toDtoList(List<OptionLigneCommande> optionLigneCommandes);

    List<OptionLigneCommande> toEntityList(List<OptionLigneCommandeDto> optionLigneCommandeDtos);
}
