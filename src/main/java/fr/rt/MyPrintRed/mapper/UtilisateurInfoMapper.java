package fr.rt.MyPrintRed.mapper;


import fr.rt.MyPrintRed.dto.InsertUtilisateurDto;
import fr.rt.MyPrintRed.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UtilisateurInfoMapper {

    InsertUtilisateurDto toDto(Utilisateur utilisateur);

    Utilisateur toUtilisateur(InsertUtilisateurDto insertUtilisateurDto);
}
