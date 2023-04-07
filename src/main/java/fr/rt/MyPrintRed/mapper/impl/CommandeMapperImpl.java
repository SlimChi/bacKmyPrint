package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.CommandeDto;
import fr.rt.MyPrintRed.dto.InsertCommandeDto;
import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.Commande;
import fr.rt.MyPrintRed.entities.Status;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.AdresseMapper;
import fr.rt.MyPrintRed.mapper.CommandeMapper;
import fr.rt.MyPrintRed.mapper.StatusMapper;
import fr.rt.MyPrintRed.mapper.UtilisateurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandeMapperImpl implements CommandeMapper {

    private final StatusMapper statusMapper;
    private final UtilisateurMapper utilisateurMapper;
    private final AdresseMapper adresseMapper;

    @Override
    public CommandeDto toDto(Commande commande) {
        return new CommandeDto(
                commande.getNumeroCommande(),
                commande.getPrixCommande(),
                commande.getDate(),
                statusMapper.toDto(commande.getStatus()),
                utilisateurMapper.toDto(commande.getUtilisateur()),
                adresseMapper.toDto(commande.getAdresse())
        );
    }

    @Override
    public Commande toEntity(CommandeDto commandeDto) {
        return new Commande(
                commandeDto.getNumeroCommande(),
                commandeDto.getPrixCommande(),
                commandeDto.getDate(),
                statusMapper.toEntity(commandeDto.getStatusDto()),
                utilisateurMapper.toUtilisateur(commandeDto.getUtilisateurDto()),
                adresseMapper.toAdresse(commandeDto.getAdresseDto())
        );
    }

    @Override
    public Commande toEntity(InsertCommandeDto insertCommandeDto) {
        Commande commande = new Commande();
        Date date = new Date(System.currentTimeMillis());

        commande.setPrixCommande(insertCommandeDto.getPrix());
        commande.setAdresse(new Adresse(insertCommandeDto.getIdAdresse(), null, null, null, null));
        commande.setUtilisateur(new Utilisateur(insertCommandeDto.getIdUtilisateur()));
        commande.setStatus(new Status(1,"EN COURS"));
        commande.setDate(date);

        return commande;
    }


    @Override
    public List<CommandeDto> toDtoList(List<Commande> commandes) {
        List<CommandeDto> dtoList = new ArrayList<>();
        commandes.stream().forEach(commande -> dtoList.add(toDto(commande)));
        return  dtoList;
    }
}
