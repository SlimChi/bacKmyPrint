package fr.rt.MyPrintRed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertFullCommandeDto {

    private InsertCommandeDto commandeDto;

    private List<InsertLigneCommandeDto> ligneCommandesDto;

}
