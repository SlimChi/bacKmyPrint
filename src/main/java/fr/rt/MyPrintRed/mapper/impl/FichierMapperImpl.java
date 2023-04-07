package fr.rt.MyPrintRed.mapper.impl;


import fr.rt.MyPrintRed.dto.FichierDto;
import fr.rt.MyPrintRed.entities.Fichier;
import fr.rt.MyPrintRed.mapper.FichierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FichierMapperImpl implements FichierMapper {


    @Override
    public FichierDto toDto(Fichier fichier) {
        return new FichierDto(
                fichier.getIdFichier(),
                fichier.getNom(),
                fichier.getTypeFichier()
        );
    }

    @Override
    public List<FichierDto> toDtoList(List<Fichier> fichiers) {
        List<FichierDto> dtoList = new ArrayList<>();
        fichiers.stream().forEach(fichier -> dtoList.add(toDto(fichier)));
        return dtoList;
    }

    @Override
    public Fichier toFichierOnlyId(FichierDto fichierDto) {
        return new Fichier(fichierDto.getIdFichier());
    }
}
