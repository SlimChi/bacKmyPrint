package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.InsertLigneCommandeDto;
import fr.rt.MyPrintRed.dto.LigneCommandeDto;
import fr.rt.MyPrintRed.entities.LigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {StatusMapper.class, FichierMapper.class})
public interface LigneCommandeMapper {

    LigneCommandeDto toDto(LigneCommande ligneCommande);

    LigneCommande toEntity(LigneCommandeDto ligneCommandeDto);

    LigneCommande toEntity(InsertLigneCommandeDto insertDto);

    List<LigneCommandeDto> toDtoList(List<LigneCommande> ligneCommandes);
}
