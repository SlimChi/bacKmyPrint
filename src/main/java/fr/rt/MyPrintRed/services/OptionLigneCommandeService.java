package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.OptionLigneCommandeDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;

import java.util.List;

public interface OptionLigneCommandeService {

    List<OptionLigneCommandeDto> getAll();

    List<OptionLigneCommandeDto> getAllByNumeros(Integer numeroCommande,Integer numeroLigneCommande);

    OptionLigneCommandeDto insert(OptionLigneCommandeDto optionLigneCommandeDto);

    void delete(OptionLigneCommandeDto optionLigneCommandeDto);


    List<OptionLigneCommandeDto> updateOptions(Integer numeroCommande,Integer numeroLigneCommande,List<TypeOptionDto> typeOptionDtos);


}
