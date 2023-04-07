package fr.rt.MyPrintRed.mapper.impl;


import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.TypeOption;
import fr.rt.MyPrintRed.entities.TypeOptionPK;
import fr.rt.MyPrintRed.mapper.TypeOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TypeOptionMapperImpl implements TypeOptionMapper {

    @Override
    public TypeOptionDto toDto(TypeOption typeOption) {

        TypeOptionDto typeOptionDto = new TypeOptionDto();;
        typeOptionDto.setIdOption(typeOption.getTypeOptionPK().getIdOption());
        typeOptionDto.setIdTypeOption(typeOption.getTypeOptionPK().getIdTypeOption());
        typeOptionDto.setLibelle(typeOption.getLibelle());
        typeOptionDto.setPrix(typeOption.getPrix());

        return typeOptionDto;
    }

    @Override
    public TypeOption toEntity(TypeOptionDto typeOptionDto) {

        TypeOption typeOption = new TypeOption();
        typeOption.setTypeOptionPK(new TypeOptionPK(
                typeOptionDto.getIdOption(), typeOptionDto.getIdTypeOption()
        ));
        typeOption.setLibelle(typeOptionDto.getLibelle());
        typeOption.setPrix(typeOptionDto.getPrix());

        return typeOption;
    }

    @Override
    public List<TypeOptionDto> toDtoList(List<TypeOption> typeOptions) {
        List<TypeOptionDto> dtoList = new ArrayList<>();
        typeOptions.stream().forEach(typeOption -> dtoList.add(toDto(typeOption)));
        return  dtoList;
    }
}
