package fr.rt.MyPrintRed.services.impl;


import fr.rt.MyPrintRed.dto.OptionDto;
import fr.rt.MyPrintRed.entities.Option;
import fr.rt.MyPrintRed.mapper.OptionMapper;
import fr.rt.MyPrintRed.repositories.OptionRepository;
import fr.rt.MyPrintRed.services.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;

    @Override
    public List<OptionDto> getOptions() {
        return optionMapper.toDtoList(optionRepository.findAll());
    }

    @Override
    public OptionDto getOptionById(Integer idOption) {
        return optionMapper.toDto(optionRepository.findById(idOption).orElseThrow());
    }

    @Override
    public OptionDto insert(OptionDto optionDto) {
        return optionMapper.toDto(optionRepository.save(optionMapper.toEntity(optionDto)));
    }

    @Override
    public OptionDto update(Integer idOption, OptionDto optionDto) {

        optionRepository.findById(idOption).orElseThrow();
        optionDto.setIdOption(idOption);
        Option option = optionMapper.toEntity(optionDto);
        return optionMapper.toDto(optionRepository.save(option));

    }

    @Override
    public void deleteById(Integer idOption) {
        optionRepository.findById(idOption).orElseThrow();
        optionRepository.deleteById(idOption);
    }
}
