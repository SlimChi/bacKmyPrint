package fr.rt.MyPrintRed.services.impl;


import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.TypeOption;
import fr.rt.MyPrintRed.entities.TypeOptionPK;
import fr.rt.MyPrintRed.exceptions.TypeOptionNotFoundException;
import fr.rt.MyPrintRed.mapper.TypeOptionMapper;
import fr.rt.MyPrintRed.repositories.TypeOptionRepository;
import fr.rt.MyPrintRed.services.TypeOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TypeOptionServiceImpl implements TypeOptionService {

    private final TypeOptionRepository repository;
    private final TypeOptionMapper mapper;

    @Override
    public List<TypeOptionDto> getTypeOptions() {
        return mapper.toDtoList(repository.getAllOrderByIdOption());
    }

    @Override
    public TypeOptionDto getById(Integer idOption,Integer idTypeOption) {
        return mapper.toDto(repository.findById(new TypeOptionPK(idOption,idTypeOption)).orElseThrow());
    }

    @Override
    public TypeOptionDto insert(TypeOptionDto typeOptionDto) {
        //verifier que l'idOption n'est pas inexistant
        Optional<Integer> index = repository.getMaxId(typeOptionDto.getIdOption());

        if(index.isPresent())
            typeOptionDto.setIdTypeOption(index.get()+1);
        else
            typeOptionDto.setIdTypeOption(1);

        return mapper.toDto(repository.save(mapper.toEntity(typeOptionDto)));

    }

    @Override
    public TypeOptionDto update(Integer idOption, Integer idTypeOption, TypeOptionDto typeOptionDto) {

        repository.findById(new TypeOptionPK(idOption,idTypeOption)).orElseThrow(()-> new TypeOptionNotFoundException(idOption,idTypeOption));
        typeOptionDto.setIdOption(idOption);
        typeOptionDto.setIdTypeOption(idTypeOption);
        TypeOption typeOption = mapper.toEntity(typeOptionDto);
        return mapper.toDto(repository.save(typeOption));

    }

    @Override
    public void deleteById(Integer idOption, Integer idTypeOption) {
        repository.findById(new TypeOptionPK(idOption,idTypeOption)).orElseThrow(()-> new TypeOptionNotFoundException(idOption,idTypeOption));
        repository.deleteById(new TypeOptionPK(idOption,idTypeOption));
    }


}
