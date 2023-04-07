package fr.rt.MyPrintRed.mapper.impl;

import fr.rt.MyPrintRed.dto.StatusDto;
import fr.rt.MyPrintRed.entities.Status;
import fr.rt.MyPrintRed.mapper.StatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatusMapperImpl implements StatusMapper {
    @Override
    public StatusDto toDto(Status status) {
        return new StatusDto(
                status.getIdStatus(),
                status.getLibelle()
        );
    }

    @Override
    public Status toEntity(StatusDto statusDto) {
        return new Status(
                statusDto.getIdStatus(),
                statusDto.getLibelle()
        );
    }

    @Override
    public List<StatusDto> toDtoList(List<Status> statuses) {
        List<StatusDto> dtoList = new ArrayList<>();

        statuses.stream().forEach(status -> dtoList.add(toDto(status)));

        return dtoList;
    }
}
