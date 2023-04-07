package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.InsertIntervenirDto;
import fr.rt.MyPrintRed.dto.IntervenirDto;
import fr.rt.MyPrintRed.entities.Intervenir;
import fr.rt.MyPrintRed.entities.IntervenirPK;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.IntervenirMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IntervenirMapperImpl implements IntervenirMapper {


    @Override
    public IntervenirDto toDto(Intervenir intervenir) {
        return new IntervenirDto(
                intervenir.getIntervenirPK().getNumeroCommande(),
                intervenir.getIntervenirPK().getNumeroLigneCommande(),
                intervenir.getIntervenirPK().getUtilisateur().getIdUtilisateur(),
                intervenir.getIntervenirPK().getUtilisateur().getNom(),
                intervenir.getIntervenirPK().getUtilisateur().getNom()
        );
    }

    @Override
    public Intervenir toEntity(IntervenirDto intervenirDto) {
        return new Intervenir(
                new IntervenirPK(intervenirDto.getNumeroCommande(), intervenirDto.getNumeroLigneCommande(),
                        new Utilisateur(intervenirDto.getIdUtilisateur()))
        );
    }

    @Override
    public Intervenir toEntity(InsertIntervenirDto insertDto) {
        return new Intervenir(
                new IntervenirPK(insertDto.getNumeroCommande(), insertDto.getNumeroLigneCommande(),
                        new Utilisateur(insertDto.getIdUtilisateur()))
        );
    }

    @Override
    public List<IntervenirDto> toDtoList(List<Intervenir> intervenirList) {
        List<IntervenirDto> dtoList = new ArrayList<>();
        intervenirList.stream().forEach(intervenir -> dtoList.add(toDto(intervenir)));
        return dtoList;
    }
}
