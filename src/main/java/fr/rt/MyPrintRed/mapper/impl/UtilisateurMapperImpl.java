package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.UtilisateurDto;
import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.UtilisateurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UtilisateurMapperImpl implements UtilisateurMapper {
    private List<Adresse> adresse;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UtilisateurDto toDto(Utilisateur utilisateur) {
        return new UtilisateurDto(

                utilisateur.getIdUtilisateur(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(),
                utilisateur.getAdresse(),
                utilisateur.getPassword(),
                utilisateur.isActive()
        );
    }

    @Override
    public Utilisateur toUtilisateur(UtilisateurDto utilisateurDto) {
        return new Utilisateur(
                utilisateurDto.getIdUtilisateur(),
                utilisateurDto.getNom(),
                utilisateurDto.getPrenom(),
                utilisateurDto.getEmail(),

                utilisateurDto.getTelephone(),
                passwordEncoder.encode(utilisateurDto.getPassword())

        );
    }



    @Override
    public List<UtilisateurDto> toListDto(List<Utilisateur> utilisateurs) {
        List<UtilisateurDto> utilisateurDtos = new ArrayList<>();
        for(Utilisateur utilisateur : utilisateurs){
            utilisateurDtos.add(toDto(utilisateur));
        }

        return utilisateurDtos;
    }
}
