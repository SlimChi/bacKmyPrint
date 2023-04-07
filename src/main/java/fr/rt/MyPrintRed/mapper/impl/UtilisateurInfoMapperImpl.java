package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.InsertUtilisateurDto;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.UtilisateurInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilisateurInfoMapperImpl implements UtilisateurInfoMapper {


    @Override
    public InsertUtilisateurDto toDto(Utilisateur utilisateur) {
        return new InsertUtilisateurDto(

                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone()

        );
    }

    @Override
    public Utilisateur toUtilisateur(InsertUtilisateurDto insertUtilisateurDto) {
        return new Utilisateur(

                insertUtilisateurDto.getNom(),
                insertUtilisateurDto.getPrenom(),
                insertUtilisateurDto.getEmail(),
                insertUtilisateurDto.getTelephone()


        );
    }
}
