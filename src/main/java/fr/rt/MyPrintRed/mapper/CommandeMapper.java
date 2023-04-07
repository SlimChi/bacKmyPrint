package fr.rt.MyPrintRed.mapper;


import fr.rt.MyPrintRed.dto.CommandeDto;
import fr.rt.MyPrintRed.dto.InsertCommandeDto;
import fr.rt.MyPrintRed.entities.Commande;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {StatusMapper.class,UtilisateurMapper.class,AdresseMapper.class})
public interface CommandeMapper {

    CommandeDto toDto(Commande commande);

    Commande toEntity(CommandeDto commandeDto);

    Commande toEntity(InsertCommandeDto insertCommandeDto);

    List<CommandeDto> toDtoList(List<Commande> commandes);
}
