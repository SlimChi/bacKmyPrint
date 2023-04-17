package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.CommandeDto;
import fr.rt.MyPrintRed.dto.InsertCommandeDto;
import fr.rt.MyPrintRed.dto.InsertFullCommandeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommandeService {

    List<CommandeDto> getCommandes();

    CommandeDto getById(Integer numeroCommande);

    List<CommandeDto> getAllByIdUtilisateur(Integer idUtilisateur);

    CommandeDto insert(InsertCommandeDto insertCommandeDto);

    CommandeDto updateStatus(Integer numeroCommande,Integer idStatus);

    CommandeDto insertFullCommande(InsertFullCommandeDto commande);
    CommandeDto insertFullCommandeFichier(InsertFullCommandeDto commande,List<MultipartFile> fichiers) throws IOException;


}
