package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.CategorieDto;

import java.util.List;

public interface CategorieService {

    CategorieDto insert(CategorieDto categorieDto);

    CategorieDto update(Integer idCategorie,CategorieDto categorieDto);

    List<CategorieDto> getCategories();

    CategorieDto getById(Integer idCategorie);

    void deleteById(Integer idCategorie);
}
