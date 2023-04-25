package fr.rt.MyPrintRed.services.impl;


import fr.rt.MyPrintRed.dto.FichierDto;
import fr.rt.MyPrintRed.entities.Fichier;
import fr.rt.MyPrintRed.exceptions.FichierNotFoundException;
import fr.rt.MyPrintRed.mapper.FichierMapper;
import fr.rt.MyPrintRed.repositories.FichierRepository;
import fr.rt.MyPrintRed.services.FichierService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class FichierServiceImpl implements FichierService {

    private final FichierRepository repository;

    private final FichierMapper mapper;

    @Override
    public Fichier store(MultipartFile file) throws IOException {

        String nomFichier = file.getOriginalFilename();
        Fichier fichier = new Fichier(0,nomFichier,file.getContentType(), file.getBytes());

        return repository.save(fichier);
    }

    @Override
    public Fichier getFile(Integer id) {
        return repository.findById(id).orElseThrow(()-> new FichierNotFoundException(id));
    }

    @Override
    public List<FichierDto> getFichiers() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public FichierDto getById(Integer idFichier) {
        return mapper.toDto(repository.findById(idFichier).orElseThrow(()-> new FichierNotFoundException(idFichier)));
    }
}
