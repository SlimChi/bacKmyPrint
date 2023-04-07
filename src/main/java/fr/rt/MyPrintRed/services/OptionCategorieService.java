package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.OptionCategorieDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.OptionCategorie;

import java.util.List;

public interface OptionCategorieService {

    List<OptionCategorieDto> getAll();

    List<OptionCategorieDto> getAllByIdCategorie(Integer idCategorie);

    OptionCategorieDto insert(OptionCategorieDto  optionCategorieDto);

    void removeTypeOptionsFromCategorie(OptionCategorieDto optionCategorieDto);

    List<OptionCategorieDto> updateOptions(Integer idCategorie,List<TypeOptionDto> typeOptionDtos);
}
