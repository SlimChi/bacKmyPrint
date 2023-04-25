package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.CategorieDto;
import fr.rt.MyPrintRed.entities.Categorie;
import fr.rt.MyPrintRed.exceptions.CategorieNotFoundException;
import fr.rt.MyPrintRed.mapper.CategorieMapper;
import fr.rt.MyPrintRed.repositories.CategorieRepository;
import fr.rt.MyPrintRed.services.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    private final CategorieMapper categorieMapper;

    @Override
    public CategorieDto insert(CategorieDto categorieDto) {
        return categorieMapper.toDto(
                categorieRepository.save(categorieMapper.toEntity(categorieDto)));
    }

    @Override
    public CategorieDto update(Integer idCategorie,CategorieDto categorieDto) {

        categorieRepository.findById(idCategorie).orElseThrow(()-> new CategorieNotFoundException(idCategorie));
        categorieDto.setIdCategorie(idCategorie);
        Categorie categorie = categorieMapper.toEntity(categorieDto);
        return categorieMapper.toDto(categorieRepository.save(categorie));

    }

    @Override
    public List<CategorieDto> getCategories() {
        return categorieMapper.toListDto(categorieRepository.findAll());
    }

    @Override
    public CategorieDto getById(Integer idCategorie) {
        return categorieMapper.toDto(categorieRepository.findById(idCategorie).orElseThrow(()-> new CategorieNotFoundException(idCategorie)));
    }

    @Override
    public void deleteById(Integer idCategorie) {
        categorieRepository.findById(idCategorie).orElseThrow(()-> new CategorieNotFoundException(idCategorie));
        categorieRepository.deleteById(idCategorie);
    }
}
