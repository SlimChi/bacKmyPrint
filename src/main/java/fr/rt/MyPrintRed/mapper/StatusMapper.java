package fr.rt.MyPrintRed.mapper;

import fr.rt.MyPrintRed.dto.StatusDto;
import fr.rt.MyPrintRed.entities.Status;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface StatusMapper {

    StatusDto toDto(Status status);

    Status toEntity(StatusDto statusDto);

    List<StatusDto> toDtoList(List<Status> statuses);
}
