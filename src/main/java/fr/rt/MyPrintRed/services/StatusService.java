package fr.rt.MyPrintRed.services;

import fr.rt.MyPrintRed.dto.StatusDto;

import java.util.List;

public interface StatusService {

    List<StatusDto> getStatuses();

    StatusDto getById(Integer idStatus);

    StatusDto insert(StatusDto statusDto);

    StatusDto update(Integer idStatus,StatusDto statusDto);

    void deleteById(Integer idStatus);
}
