package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.OptionDto;
import fr.rt.MyPrintRed.entities.Option;
import fr.rt.MyPrintRed.mapper.OptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OptionMapperImpl implements OptionMapper {
    @Override
    public OptionDto toDto(Option option) {
        return new OptionDto(
                option.getIdOption(),
                option.getLibelle()
        );
    }

    @Override
    public Option toEntity(OptionDto optionDto) {
        return new Option(
                optionDto.getIdOption(),
                optionDto.getLibelle()
        );
    }

    @Override
    public List<OptionDto> toDtoList(List<Option> options) {
        List<OptionDto> dtoList = new ArrayList<>();
        options.stream().forEach(option -> dtoList.add(toDto(option)));
        return dtoList;
    }
}
