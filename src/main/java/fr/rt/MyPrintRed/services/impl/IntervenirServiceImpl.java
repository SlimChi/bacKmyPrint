package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.InsertIntervenirDto;
import fr.rt.MyPrintRed.dto.IntervenirDto;
import fr.rt.MyPrintRed.entities.Intervenir;
import fr.rt.MyPrintRed.entities.Utilisateur;
import fr.rt.MyPrintRed.mapper.IntervenirMapper;
import fr.rt.MyPrintRed.repositories.IntervenirRepository;
import fr.rt.MyPrintRed.repositories.UtilisateurRepository;
import fr.rt.MyPrintRed.services.IntervenirService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IntervenirServiceImpl implements IntervenirService {

    private final IntervenirRepository repository;
    private final IntervenirMapper mapper;

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public List<IntervenirDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public List<IntervenirDto> getAllByNumeroCommande(Integer numeroCommande) {
        return mapper.toDtoList(repository.getAllByNumeroCommande(numeroCommande));
    }


    @Override
    public IntervenirDto insert(InsertIntervenirDto insertDto) {

        Utilisateur utilisateur = utilisateurRepository.getReferenceById(insertDto.getIdUtilisateur());
        Intervenir intervenir = mapper.toEntity(insertDto);
        intervenir.getIntervenirPK().setUtilisateur(utilisateur);

        return mapper.toDto(repository.save(intervenir));
    }
}
