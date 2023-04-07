package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.InsertLigneCommandeDto;
import fr.rt.MyPrintRed.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {


    List<LigneCommandeDto> getAll();

    List<LigneCommandeDto> getAllByNumeroCommande(Integer numeroCommande);

    LigneCommandeDto getAllByNumeros(Integer numeroCommande,Integer numeroLigneCommande);

    LigneCommandeDto insert(InsertLigneCommandeDto insertDto);

    LigneCommandeDto updateStatus(Integer numeroCommande,Integer numeroLigneCommande,Integer idStatus);
}
