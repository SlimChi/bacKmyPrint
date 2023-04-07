package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.TypeOptionDto;


import java.util.List;

public interface TypeOptionService {

    List<TypeOptionDto> getTypeOptions();

    TypeOptionDto getById(Integer idOption,Integer idTypeOption);

    TypeOptionDto insert(TypeOptionDto typeOptionDto);

    TypeOptionDto update(Integer idOption,Integer idTypeOption,TypeOptionDto typeOptionDto);

    void deleteById(Integer idOption,Integer idTypeOption);



}
