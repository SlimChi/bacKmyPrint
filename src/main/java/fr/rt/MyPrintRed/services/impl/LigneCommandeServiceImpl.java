package fr.rt.MyPrintRed.services.impl;


import fr.rt.MyPrintRed.dto.InsertLigneCommandeDto;
import fr.rt.MyPrintRed.dto.LigneCommandeDto;
import fr.rt.MyPrintRed.entities.LigneCommande;
import fr.rt.MyPrintRed.entities.LigneCommandePK;
import fr.rt.MyPrintRed.entities.Status;
import fr.rt.MyPrintRed.exceptions.LigneCommandeNotFoundException;
import fr.rt.MyPrintRed.mapper.LigneCommandeMapper;
import fr.rt.MyPrintRed.repositories.LigneCommandeRepository;
import fr.rt.MyPrintRed.repositories.StatusRepository;
import fr.rt.MyPrintRed.services.LigneCommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;

    private final LigneCommandeMapper mapper;

    private final StatusRepository statusRepository;

    @Override
    public List<LigneCommandeDto> getAll() {
        return mapper.toDtoList(ligneCommandeRepository.findAll());
    }

    @Override
    public List<LigneCommandeDto> getAllByNumeroCommande(Integer numeroCommande) {
        return mapper.toDtoList(ligneCommandeRepository.getAllByNumeroCommande(numeroCommande));
    }

    @Override
    public LigneCommandeDto getAllByNumeros(Integer numeroCommande, Integer numeroLigneCommande) {
        return mapper.toDto(ligneCommandeRepository.findById(new LigneCommandePK(numeroCommande,numeroLigneCommande))
                .orElseThrow(()-> new LigneCommandeNotFoundException(numeroCommande,numeroLigneCommande)));
    }

    @Override
    public LigneCommandeDto insert(InsertLigneCommandeDto insertDto) {

        Optional<Integer> index = ligneCommandeRepository.getMaxId(insertDto.getNumeroCommande());
        LigneCommande ligneCommande = mapper.toEntity(insertDto);

        if(index.isPresent())
            ligneCommande.getLigneCommandePK().setNumeroLigneCommande(index.get() + 1);
        else
            ligneCommande.getLigneCommandePK().setNumeroLigneCommande(1);


        return mapper.toDto(ligneCommandeRepository.save(ligneCommande));

    }

    @Override
    public LigneCommandeDto updateStatus(Integer numeroCommande, Integer numeroLigneCommande, Integer idStatus) {

        LigneCommande ligneCommande = mapper.toEntity(getAllByNumeros(numeroCommande,numeroLigneCommande));

        Status status = statusRepository.getById(idStatus);
        ligneCommande.setStatus(status);


        return mapper.toDto(ligneCommandeRepository.save(ligneCommande));
    }
}
