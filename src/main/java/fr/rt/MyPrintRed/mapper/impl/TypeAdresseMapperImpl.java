package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.TypeAdresseDto;
import fr.rt.MyPrintRed.entities.TypeAdresse;
import fr.rt.MyPrintRed.mapper.TypeAdresseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeAdresseMapperImpl implements TypeAdresseMapper {
    @Override
    public TypeAdresseDto toDto(TypeAdresse typeAdresse) {
        return new TypeAdresseDto(
                typeAdresse.getId(),
                typeAdresse.getLibelle()
        );
    }

    @Override
    public TypeAdresse toEntity(TypeAdresseDto typeAdresseDto) {
        return new TypeAdresse(
                typeAdresseDto.getId(),
                typeAdresseDto.getLibelle()
        );
    }
}
