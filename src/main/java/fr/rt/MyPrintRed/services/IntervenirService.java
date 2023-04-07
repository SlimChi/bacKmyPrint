package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.InsertIntervenirDto;
import fr.rt.MyPrintRed.dto.IntervenirDto;

import java.util.List;

public interface IntervenirService {

    List<IntervenirDto> getAll();

    List<IntervenirDto> getAllByNumeroCommande(Integer numeroCommande);

    IntervenirDto insert(InsertIntervenirDto insertDto);
}
