package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.OptionDto;

import java.util.List;

public interface OptionService {


    List<OptionDto> getOptions();

    OptionDto getOptionById(Integer idOption);

    OptionDto insert(OptionDto optionDto);

    OptionDto update(Integer idOption,OptionDto optionDto);

    void deleteById(Integer idOption);
}
