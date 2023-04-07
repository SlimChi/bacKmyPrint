package fr.rt.MyPrintRed.services.impl;

import fr.rt.MyPrintRed.dto.StatusDto;
import fr.rt.MyPrintRed.entities.Status;
import fr.rt.MyPrintRed.mapper.StatusMapper;
import fr.rt.MyPrintRed.repositories.StatusRepository;
import fr.rt.MyPrintRed.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    private final StatusMapper statusMapper;


    @Override
    public List<StatusDto> getStatuses() {
        return statusMapper.toDtoList(statusRepository.findAll());
    }

    @Override
    public StatusDto getById(Integer idStatus) {
        return statusMapper.toDto(statusRepository.findById(idStatus).orElseThrow());
    }

    @Override
    public StatusDto insert(StatusDto statusDto) {
        return statusMapper.toDto(
                statusRepository.save(statusMapper.toEntity(statusDto))
        );
    }

    @Override
    public StatusDto update(Integer idStatus, StatusDto statusDto) {

        statusRepository.findById(idStatus).orElseThrow();
        statusDto.setIdStatus(idStatus);
        Status status = statusMapper.toEntity(statusDto);
        return statusMapper.toDto(statusRepository.save(status));

    }

    @Override
    public void deleteById(Integer idStatus) {
        statusRepository.findById(idStatus).orElseThrow();
        statusRepository.deleteById(idStatus);
    }
}
