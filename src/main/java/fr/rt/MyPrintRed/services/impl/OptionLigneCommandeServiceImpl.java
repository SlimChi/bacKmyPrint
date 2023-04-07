package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.OptionLigneCommandeDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.OptionLigneCommande;
import fr.rt.MyPrintRed.mapper.OptionLigneCommandeMapper;
import fr.rt.MyPrintRed.repositories.OptionLigneCommandeRepository;
import fr.rt.MyPrintRed.services.OptionLigneCommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OptionLigneCommandeServiceImpl implements OptionLigneCommandeService {

    private final OptionLigneCommandeRepository repository;
    private final OptionLigneCommandeMapper mapper;

    @Override
    public List<OptionLigneCommandeDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public List<OptionLigneCommandeDto> getAllByNumeros(Integer numeroCommande, Integer numeroLigneCommande) {
        return mapper.toDtoList(repository.getAllByNumeros(numeroCommande, numeroLigneCommande));
    }

    @Override
    public OptionLigneCommandeDto insert(OptionLigneCommandeDto optionLigneCommandeDto) {
        return mapper.toDto(repository.save(mapper.toEntity(optionLigneCommandeDto)));
    }

    @Override
    public void delete(OptionLigneCommandeDto optionLigneCommandeDto) {

        repository.findById(mapper.toEntity(optionLigneCommandeDto).getOptionLigneCommandePK()).orElseThrow();
        repository.delete(mapper.toEntity(optionLigneCommandeDto));
    }



    @Override
    public List<OptionLigneCommandeDto> updateOptions(Integer numeroCommande, Integer numeroLigneCommande, List<TypeOptionDto> typeOptionDtos) {


        repository.deleteByOptionLigneCommandePK_NumeroCommandeAndAndOptionLigneCommandePK_NumeroLigneCommande(numeroCommande, numeroLigneCommande);

        for (TypeOptionDto typeOptionDto : typeOptionDtos) {

            OptionLigneCommande optionLigneCommande = mapper.toEntity(numeroCommande, numeroLigneCommande, typeOptionDto);
            repository.save(optionLigneCommande);
        }


        return mapper.toDtoList(repository.getAllByNumeros(numeroCommande,numeroLigneCommande));

    }


}
