package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.CategorieDto;
import fr.rt.MyPrintRed.entities.Categorie;
import fr.rt.MyPrintRed.mapper.CategorieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorieMapperImpl implements CategorieMapper {
    @Override
    public CategorieDto toDto(Categorie categorie) {
        return new CategorieDto(
                categorie.getIdCategorie(),
                categorie.getLibelle()
        );
    }

    @Override
    public Categorie toEntity(CategorieDto categorieDto) {
        return new Categorie(
                categorieDto.getIdCategorie(),
                categorieDto.getLibelle()
        );
    }

    @Override
    public List<CategorieDto> toListDto(List<Categorie> categories) {
        List<CategorieDto> dtoList = new ArrayList<>();

        categories.stream().forEach(categorie -> dtoList.add(toDto(categorie)));


        return dtoList;
    }
}
