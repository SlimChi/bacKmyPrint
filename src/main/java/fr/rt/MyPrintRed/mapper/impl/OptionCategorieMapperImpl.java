package fr.rt.MyPrintRed.mapper.impl;


import fr.rt.MyPrintRed.dto.OptionCategorieDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.OptionCategorie;
import fr.rt.MyPrintRed.entities.OptionCategoriePK;
import fr.rt.MyPrintRed.entities.TypeOption;
import fr.rt.MyPrintRed.mapper.OptionCategorieMapper;
import fr.rt.MyPrintRed.mapper.TypeOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OptionCategorieMapperImpl implements OptionCategorieMapper {

    private final TypeOptionMapper toMapper;

    @Override
    public OptionCategorieDto toDto(OptionCategorie optionCategorie) {

        OptionCategorieDto dto = new OptionCategorieDto();

        dto.setIdCategorie(optionCategorie.getOptionCategoriePK().getIdCategorie());
        dto.setTypeOptionDto(toMapper.toDto(optionCategorie.getOptionCategoriePK().getTypeOption()));

        return dto;
    }

    @Override
    public OptionCategorie toEntity(OptionCategorieDto optionCategorieDto) {

        OptionCategorie optionCategorie = new OptionCategorie();
        optionCategorie.setOptionCategoriePK(new OptionCategoriePK(
                optionCategorieDto.getIdCategorie(),
                toMapper.toEntity(optionCategorieDto.getTypeOptionDto())
        ));


        return optionCategorie;
    }

    @Override
    public OptionCategorie toEntity(Integer idCategorie, TypeOptionDto typeOptionDto) {
        return new OptionCategorie(
                new OptionCategoriePK(
                        idCategorie,
                        toMapper.toEntity(typeOptionDto)
                )
        );
    }

    @Override
    public OptionCategorie toEntityWithIds(Integer idCategorie, Integer idOption, Integer idTypeOption) {

        OptionCategorie optionCategorie = new OptionCategorie();



        return null;
    }

    @Override
    public List<OptionCategorieDto> toDtoList(List<OptionCategorie> optionCategories) {
        List<OptionCategorieDto> dtoList = new ArrayList<>();
        optionCategories.stream().forEach(optionCategorie -> dtoList.add(toDto(optionCategorie)));
        return dtoList;
    }
}
