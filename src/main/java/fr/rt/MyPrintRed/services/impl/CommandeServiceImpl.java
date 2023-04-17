package fr.rt.MyPrintRed.services.impl;


import fr.rt.MyPrintRed.dto.*;
import fr.rt.MyPrintRed.entities.Commande;
import fr.rt.MyPrintRed.entities.Fichier;
import fr.rt.MyPrintRed.entities.Status;
import fr.rt.MyPrintRed.mapper.CommandeMapper;
import fr.rt.MyPrintRed.mapper.LigneCommandeMapper;
import fr.rt.MyPrintRed.repositories.CommandeRepository;
import fr.rt.MyPrintRed.repositories.StatusRepository;
import fr.rt.MyPrintRed.services.CommandeService;
import fr.rt.MyPrintRed.services.FichierService;
import fr.rt.MyPrintRed.services.LigneCommandeService;
import fr.rt.MyPrintRed.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;

    private final LigneCommandeService ligneCommandeService;

    private final StatusRepository statusRepository;

    private final FichierService fichierService;


    @Override
    public List<CommandeDto> getCommandes() {
        return commandeMapper.toDtoList(commandeRepository.findAll());
    }

    @Override
    public CommandeDto getById(Integer numeroCommande) {
        return commandeMapper.toDto(commandeRepository.findById(numeroCommande).orElseThrow());
    }

    @Override
    public List<CommandeDto> getAllByIdUtilisateur(Integer idUtilisateur) {
        return commandeMapper.toDtoList(commandeRepository.getCommandesByIdUtilisateur(idUtilisateur));
    }

    @Override
    public CommandeDto insert(InsertCommandeDto insertCommandeDto) {

        Optional<Integer> index = commandeRepository.getMaxId();
        Commande commande = commandeMapper.toEntity(insertCommandeDto);

        if(index.isPresent())
            commande.setNumeroCommande(index.get()+1);
        else
            commande.setNumeroCommande(1);

        return commandeMapper.toDto(commandeRepository.save(commande));

    }

    @Override
    public CommandeDto updateStatus(Integer numeroCommande, Integer idStatus) {

        Commande commande = commandeRepository.findById(numeroCommande).orElseThrow();

        Status status = statusRepository.getById(idStatus);
        commande.setStatus(status);

        return commandeMapper.toDto(commandeRepository.save(commande));

    }

    @Override
    public CommandeDto insertFullCommande(InsertFullCommandeDto commande) {

        CommandeDto commandeDto = insert(commande.getCommandeDto());


        for(InsertLigneCommandeDto ligne : commande.getLigneCommandesDto()){
            ligne.setNumeroCommande(commandeDto.getNumeroCommande());
            ligneCommandeService.insert(ligne);
        }

        return commandeDto;
    }

    @Override
    public CommandeDto insertFullCommandeFichier(InsertFullCommandeDto commande, List<MultipartFile> fichiers) throws IOException {
        CommandeDto commandeDto = insert(commande.getCommandeDto());

        for(int i=0;i< fichiers.size();i++){
            Fichier fichier = fichierService.store(fichiers.get(i));
            commande.getLigneCommandesDto().get(i).setIdFichier(fichier.getIdFichier());
        }

        for(InsertLigneCommandeDto ligne : commande.getLigneCommandesDto()){

            ligne.setNumeroCommande(commandeDto.getNumeroCommande());
            ligneCommandeService.insert(ligne);
        }

        return commandeDto;
    }

}
